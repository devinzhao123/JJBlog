package wang.dreamland.www.dao;

import wang.dreamland.www.entity.UserContent;

public interface UserContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserContent record);

    int insertSelective(UserContent record);

    UserContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserContent record);

    int updateByPrimaryKeyWithBLOBs(UserContent record);

    int updateByPrimaryKey(UserContent record);
}