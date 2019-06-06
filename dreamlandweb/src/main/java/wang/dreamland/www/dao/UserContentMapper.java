package wang.dreamland.www.dao;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import wang.dreamland.www.entity.UserContent;

import java.util.List;

public interface UserContentMapper extends Mapper<UserContent> {
    int deleteByPrimaryKey(Long id);

    int insert(UserContent record);

    int insertSelective(UserContent record);

    UserContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserContent record);

    int updateByPrimaryKeyWithBLOBs(UserContent record);

    int updateByPrimaryKey(UserContent record);

    /**
     * 根据用户id查询出博客分类
     * @param uid
     * @return
     */
    List<UserContent> findCategoryByUid(@Param("uid")long uid);

    /**
     * 插入文章并返回主键id 返回类型只是影响行数  id在UserContent对象中
     * @param userContent
     * @return
     */
    int insertContent(UserContent userContent);

    /**
     * user_content与user连接查询
     * @param userContent
     * @return
     */
    List<UserContent> findByJoin(UserContent userContent);
}