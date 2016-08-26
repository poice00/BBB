import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mb.domain.Commentrelation;
import com.mb.domain.Repostrelation;
import com.mb.domain.User;
import com.mb.service.BlogService;
import com.mb.service.CommentrelationService;
import com.mb.service.InfluenceService;
import com.mb.service.RepostrelationService;
import com.mb.service.UserService;
//import sun.net.www.protocol.gopher.GopherClient;

/**
 * Spring的单元测试
 * @author sys
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration
(locations = { "classpath*:applicationContext.xml", "classpath*:spring-context.xml" }) 
 //加载配置文件  
//-
public class BaseJunit4Test {
	@Resource
    ApplicationContext ac;
	
	@Resource
	private RepostrelationService repostrelationService;
	@Resource
	private CommentrelationService commentrelationService;
	@Resource
	private BlogService blogService;
	@Resource
	private InfluenceService influenceService;
	
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sf);
		
	}
	@Test
	public void testRepostrelation() throws Exception{
		List<Repostrelation> repostrelationList = repostrelationService.findAll();
		for (Repostrelation repostrelation : repostrelationList) {
			String muid = blogService.getById(repostrelation.getId().getMid()).getUser().getId();
			repostrelation.setMuid(muid);
			repostrelationService.update(repostrelation);
		}
	}
	@Test
	public void testCommentrelation() throws Exception{
		List<Commentrelation> CommentrelationList = commentrelationService.findAll();
		for (Commentrelation commentrelation : CommentrelationList) {
			String muid = blogService.getById(commentrelation.getMid()).getUser().getId();
			commentrelation.setMuid(muid);
			commentrelationService.update(commentrelation);
		}
	}
	@Test
	public void testInf() throws Exception{
		String path = "/result/";
	}
}
