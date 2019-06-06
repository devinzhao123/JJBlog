package wang.dreamland.www.dao;

import wang.dreamland.www.entity.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}