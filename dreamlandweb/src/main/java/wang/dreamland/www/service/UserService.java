package wang.dreamland.www.service;

import wang.dreamland.www.entity.User;

public interface UserService {
    //用户注册
    int regist(User user);
    //用户登陆
    User login(String email,String password);
    //根据用户邮箱查询用户
    User findByEmail(String email);
    //根据用户手机号查询用户
    User findByPhone(String phone);
    //根据id查询用户
    User findById(Long id);
    //根据邮箱账号删除用户
    void deleteByEmail(String email);
    //更新用户信息
    void update(User user);
}
