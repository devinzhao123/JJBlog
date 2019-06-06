package wang.dreamland.www.dao;

import tk.mybatis.mapper.common.Mapper;
import wang.dreamland.www.entity.UserInfo;

public interface UserInfoMapper extends Mapper<UserInfo> {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}