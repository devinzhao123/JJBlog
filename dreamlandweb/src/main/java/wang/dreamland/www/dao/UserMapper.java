package wang.dreamland.www.dao;

import tk.mybatis.mapper.common.Mapper;
import wang.dreamland.www.entity.User;

public interface UserMapper extends Mapper<User> {
    int insert(User record);

    int insertSelective(User record);
}