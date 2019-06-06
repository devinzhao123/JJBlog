package wang.dreamland.www.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.dreamland.www.dao.UserMapper;
import wang.dreamland.www.entity.User;
import wang.dreamland.www.service.UserService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public int regist(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User login(String name, String password) {
        User user = new User();
        user.setEmail(name);
        user.setPassword(password);
        return userMapper.selectOne(user);
        //return userMapper.findUserByNameAndPwd(name,password);
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return userMapper.selectOne(user);
    }

    @Override
    public User findByPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        return userMapper.selectOne(user);
    }

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(id);
        return userMapper.selectOne(user);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        userMapper.delete(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
