import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import wang.dreamland.www.entity.UserContent;
import wang.dreamland.www.service.UserContentService;

import java.io.IOException;
import java.util.List;

@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml","classpath:spring-mybatis.xml","classpath:applicationContext-activemq.xml",
        "classpath:applicationContext-solr.xml"})
public class TestSolrJ extends AbstractJUnit4SpringContextTests {
    @Autowired
    private SolrClient solrServer;
   @Autowired
   private UserContentService userContentService;

    @Test
    public void testSave() throws Exception{
        //创建一个文档对象
        SolrInputDocument inputDocument = new SolrInputDocument();
        inputDocument.addField("id","35");
        inputDocument.addField("title","ssm项目");
        inputDocument.addField("item_title","ssm项目吉吉博客");
        inputDocument.addField("item_image","www.ssm.png");
        inputDocument.addField("author","zcr");
        //将文档写入索引库中
        solrServer.add(inputDocument);
        //提交
        solrServer.commit();
    }

    @Test
    public void testSaveAll() throws IOException, SolrServerException{
        List<UserContent> list = userContentService.findAll();
        if(list!=null&&list.size()>0){
            for(UserContent cont:list){
                SolrInputDocument inputDocument = new SolrInputDocument();
                inputDocument.addField( "comment_num", cont.getCommentNum() );
                inputDocument.addField( "downvote", cont.getDownvote() );
                inputDocument.addField( "upvote", cont.getUpvote() );
                inputDocument.addField( "nick_name", cont.getNickName());
                inputDocument.addField( "img_url", cont.getImgUrl() );
                inputDocument.addField( "rpt_time", cont.getRptTime() );
                inputDocument.addField( "content", cont.getContent() );
                inputDocument.addField( "category", cont.getCategory());
                inputDocument.addField( "title", cont.getTitle() );
                inputDocument.addField( "u_id", cont.getuId() );
                inputDocument.addField( "id", cont.getId());
                inputDocument.addField( "personal", cont.getPersonal());
                solrServer.add( inputDocument );
            }
        }
        solrServer.commit();
    }
}
