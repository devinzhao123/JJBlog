package wang.dreamland.www.service;
import wang.dreamland.www.common.PageHelper;
import wang.dreamland.www.common.PageHelper.Page;
import wang.dreamland.www.entity.UserContent;

public interface SolrService {

    /**
     * 根据关键字搜索文章并分页
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<UserContent> findByKeyWords(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 添加文章到solr索引库
     * @param userContent
     */
    void addUserContent(UserContent userContent);

    /**
     * 根据solr索引库
     * @param userContent
     */
    void updateUserContent(UserContent userContent);

    /**
     * 根据文章id删除索引库
     * @param id
     */
    void deleteById(Long id);

}
