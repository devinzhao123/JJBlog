package wang.dreamland.www.dao;



import tk.mybatis.mapper.common.Mapper;
import wang.dreamland.www.entity.Resource;

public interface ResourceMapper extends Mapper<Resource> {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}