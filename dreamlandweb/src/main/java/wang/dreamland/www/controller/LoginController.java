package wang.dreamland.www.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.dreamland.www.common.Constants;
import wang.dreamland.www.common.MD5Util;
import wang.dreamland.www.common.RandStringUtils;
import wang.dreamland.www.entity.User;
import wang.dreamland.www.service.UserService;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController extends BaseController {
    private static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired //redis数据库操作模板
    private RedisTemplate<String,String> redisTemplate;//jdbcTemplate Hibernate Template

    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsTemplate;//mq消息模板


    @RequestMapping("/login")
    public String login(Model model){
        User user = (User)getSession().getAttribute("user");
        if(user!=null){
            return "/personal/personal";
        }
        return "../login";
    }
    @RequestMapping("/doLogin")
    public String doLogin(Model model, @RequestParam(value = "username",required = false)String email,
                          @RequestParam(value = "password",required = false)String password,
                          @RequestParam(value = "code",required = false)String code,
                          @RequestParam(value = "telephone",required = false)String telephone,
                          @RequestParam(value = "phone_code",required = false)String phone_code,
                          @RequestParam(value = "state",required = false)String state,
                          @RequestParam(value = "pageNum",required = false)Integer pageNum,
                          @RequestParam(value = "pageSize",required = false)Integer pageSize){
        //判断是否是手机登陆
        if(StringUtils.isNotBlank(telephone)){
            //手机登陆
            String yzm = redisTemplate.opsForValue().get(telephone);
            System.out.println("测试点2---"+yzm);
            System.out.println("测试点3---"+phone_code);
            if(phone_code.equals(yzm)){
                //验证码正确
                User user = userService.findByPhone(telephone);
                getSession().setAttribute("user",user);
                model.addAttribute("user",user);
                logger.info("手机快捷登陆成功");
                return "redirect:/list";
            }else {
                //验证码错误或者过期
                model.addAttribute("error","phone_fail");
                return "../login";
            }
        }else {
            //账号登陆
            if (StringUtils.isBlank(code)) {
                model.addAttribute("error", "fail");
                return "../login";
            }
            int b = checkValidateCode(code);
            if (b == -1) {
                model.addAttribute("error", "fail");
                return "../login";
            } else if (b == 0) {
                model.addAttribute("error", "fail");
                return "../login";
            }
            password = MD5Util.encodeToHex(Constants.SALT + password);
            User user = userService.login(email, password);
            if (user != null) {
                if ("0".equals(user.getState())) {
                    //未激活
                    model.addAttribute("email", email);
                    model.addAttribute("error", "active");
                    return "../login";
                }
                logger.info("用户登陆登陆成功");
                getSession().setAttribute("user", user);
                model.addAttribute("user", user);
                return "redirect:/list";
            } else {
                logger.info("用户登陆登陆失败");
                model.addAttribute("email", email);
                model.addAttribute("error", "fail");
                return "../login";
            }
        }
    }

    //匹配验证码的正确性
    public int checkValidateCode(String code){
        Object vercode = getRequest().getSession().getAttribute("VERCODE_KEY");
        if(null == vercode){
            return -1;
        }
        if(!code.equalsIgnoreCase(vercode.toString())){
            return 0;
        }
            return 1;
    }

    /**
     * 发送手机验证码
     * @param model
     * @param telephone
     * @return
     */
    @RequestMapping("/sendSms")
    @ResponseBody
    public Map<String,Object> index(Model model,@RequestParam(value = "telephone",
            required = false) final String telephone){
        Map map = new HashMap<String,Object>();
        try{//发送验证码操作
            final String code = RandStringUtils.getCode();
            System.out.println("测试-验证码："+code);
            System.out.println("测试-手机号："+telephone);
            redisTemplate.opsForValue().set(telephone,code,60, TimeUnit.SECONDS); //60秒有效redis保存验证码
            System.out.println("测试点1--pass");
            logger.debug("-------短信验证码为："+code);
            //调用ActiveMQ jmsTemplate,发送一条信息给MQ
            jmsTemplate.send("login_msg", new MessageCreator() {
                @Override
                public Message createMessage(javax.jms.Session session) throws JMSException {
                    MapMessage mapMessage = session.createMapMessage();
                    mapMessage.setString("telephone",telephone);
                    mapMessage.setString("code",code);
                    return mapMessage;
                }
            });
        }catch (Exception e){
            map.put("msg",false);
        }
        map.put("msg",true);
        return map;
    }
    //退出登录
    @RequestMapping("/loginout")
    public String exit(Model model){
        logger.info("退出登录");
        getSession().removeAttribute("user");
        getSession().invalidate();
        return "../login";
    }
}
