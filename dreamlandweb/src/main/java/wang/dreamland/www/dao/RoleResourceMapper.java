package wang.dreamland.www.dao;

import tk.mybatis.mapper.common.Mapper;
import wang.dreamland.www.entity.RoleResource;

public interface RoleResourceMapper extends Mapper<RoleResource> {
    int deleteByPrimaryKey(Long id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    RoleResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
}