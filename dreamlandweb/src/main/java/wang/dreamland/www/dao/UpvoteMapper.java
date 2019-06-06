package wang.dreamland.www.dao;

import tk.mybatis.mapper.common.Mapper;
import wang.dreamland.www.entity.Upvote;

public interface UpvoteMapper extends Mapper<Upvote> {
    int deleteByPrimaryKey(Long id);

    int insert(Upvote record);

    int insertSelective(Upvote record);

    Upvote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Upvote record);

    int updateByPrimaryKey(Upvote record);
}