import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mb.domain.Influence;
import com.mb.domain.ResultCenter;
import com.mb.domain.ResultMax;
import com.mb.domain.ResultMyPR;
import com.mb.domain.ResultPR;
import com.mb.domain.User;
import com.mb.service.BlogService;
import com.mb.service.CommentrelationService;
import com.mb.service.InfluenceService;
import com.mb.service.RepostrelationService;
import com.mb.service.ResultCenterService;
import com.mb.service.ResultMaxService;
import com.mb.service.ResultMyPRService;
import com.mb.service.ResultPRService;
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
	private UserService userService;
	@Resource
	private CommentrelationService commentrelationService;
	@Resource
	private BlogService blogService;
	@Resource
	private ResultMyPRService resultMyPRService;
	@Resource
	private ResultPRService resultPRService;
	@Resource
	private ResultCenterService resultCenterService;
	@Resource
	private ResultMaxService resultMaxService;
	@Resource
	private InfluenceService influenceService;
	
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sf);
		
	}
	@Test
	public void testRepostrelation() throws Exception{
//		List<Repostrelation> repostrelationList = repostrelationService.findAll();
//		for (Repostrelation repostrelation : repostrelationList) {
//			String muid = blogService.getById(repostrelation.getId().getMid()).getUser().getId();
//			repostrelation.setMuid(muid);
//			repostrelationService.update(repostrelation);
//		}
	}
	@Test
	public void testCommentrelation() throws Exception{
//		List<Commentrelation> CommentrelationList = commentrelationService.findAll();
//		for (Commentrelation commentrelation : CommentrelationList) {
//			String muid = blogService.getById(commentrelation.getMid()).getUser().getId();
//			commentrelation.setMuid(muid);
//			commentrelationService.update(commentrelation);
//		}
	}
	@Test
	public void update() throws Exception{
		List<ResultPR> datlist = resultPRService.findAll();
		for (ResultPR resultPR : datlist) {
			User u = userService.getById(resultPR.getId());
			resultPR.setName(u.getName());
			resultPRService.update(resultPR);
		}
		List<ResultCenter> datlist2 = resultCenterService.findAll();
		for (ResultCenter resultPR : datlist2) {
			User u = userService.getById(resultPR.getId());
			resultPR.setName(u.getName());
			resultCenterService.update(resultPR);
		}
		List<ResultMax> datlist3 = resultMaxService.findAll();
		for (ResultMax resultPR : datlist3) {
			User u = userService.getById(resultPR.getId());
			resultPR.setName(u.getName());
			resultMaxService.update(resultPR);
		}
		List<ResultMyPR> datlist4 = resultMyPRService.findAll();
		for (ResultMyPR resultPR : datlist4) {
			User u = userService.getById(resultPR.getId());
			resultPR.setName(u.getName());
			resultMyPRService.update(resultPR);
		}
	}
	@Test
	public void testInf() throws Exception{
		String result = "result/result0.0001";
		String ori_result = "result/ori_result0.0001";
		String centerDist_result = "result/centerDist_result";
		String maxDegree_result = "result/maxDegree_result";
		//result
//		List<String> dataList = readTo(result);
//		for (String data : dataList) {
//			ResultMyPR my = new ResultMyPR();
//			my.setId(data.split("\t")[0]);
//			my.setValue(data.split("\t")[1]);
//			resultMyPRService.save(my);
//		}
		//ori_result
//		List<String> dataList = readTo(ori_result);
//		for (String data : dataList) {
//			ResultPR my = new ResultPR();
//			my.setId(data.split("\t")[0]);
//			my.setValue(data.split("\t")[1]);
//			resultPRService.save(my);
//		}
		//centerDist_result
//		List<String> dataList = readTo(centerDist_result);
//		for (String data : dataList) {
//			ResultCenter my = new ResultCenter();
//			my.setId(data.split("\t")[0]);
//			my.setValue(data.split("\t")[1]);
//			resultCenterService.save(my);
//		}
		//maxDegree_result
//		List<String> dataList = readTo(maxDegree_result);
//		for (String data : dataList) {
//			ResultMax my = new ResultMax();
//			my.setId(data.split("\t")[0]);
//			my.setValue(data.split("\t")[1]);
//			resultMaxService.save(my);
//		}
	}
	private List<String> readTo(String path) {
		List<String> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				list.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
