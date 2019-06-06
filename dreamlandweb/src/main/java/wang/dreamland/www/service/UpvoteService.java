package wang.dreamland.www.service;

import wang.dreamland.www.entity.Upvote;

public interface UpvoteService {
    /**
     * 根据用户id和文章id查询
     * @param upvote
     * @return
     */
    Upvote findByUidAndConId(Upvote upvote);
    /**
     * 添加upvote
     * @param upvote
     * @return
     */
    int add(Upvote upvote);
    /**
     * 根据用户id查询最后一次登录的Upvote
     * @param upvote
     * @return
     */
    Upvote getByUid(Upvote upvote);
    /**
     * 更新Upvote
     * @param upvote
     */
    void update(Upvote upvote);

    /**
     * 根据文章id删除Upvote
     * @param cid
     */
    void deleteByContentId(Long cid);
}
