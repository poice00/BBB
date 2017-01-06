import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mb.domain.Blog;
import com.mb.domain.Commentrelation;
import com.mb.domain.Influence;
import com.mb.domain.Repostrelation;
import com.mb.domain.ResultCenter;
import com.mb.domain.ResultKS;
import com.mb.domain.ResultMax;
import com.mb.domain.ResultMyPR;
import com.mb.domain.ResultPR;
import com.mb.domain.User;
import com.mb.domain.Userrelation;
import com.mb.domain.UserrelationId;
import com.mb.service.BlogService;
import com.mb.service.CommentrelationService;
import com.mb.service.InfluenceService;
import com.mb.service.RepostrelationService;
import com.mb.service.ResultCenterService;
import com.mb.service.ResultKSService;
import com.mb.service.ResultMaxService;
import com.mb.service.ResultMyPRService;
import com.mb.service.ResultPRService;
import com.mb.service.UserService;
import com.mb.service.UserrelationService;
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
	private UserrelationService userrelationService;
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
	private ResultKSService resultKSService;
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
	public void calczan() throws Exception {
		List<User> userlist = userService.findAll();
		for (User user : userlist) {
			Set<Blog> blogs = user.getBlogs();
			int zannum = 0 ;
			for (Blog blog : blogs) {
				zannum += Integer.parseInt(blog.getAttitudesCount());
			}
			user.setZanNum(zannum);
			userService.update(user);
			System.out.println("1111");
		}
		
	}
//	由于当 x=6 的时候该函数已经非常接近 1，而且随着 x 继续增大该值变化 特别小，所以通过归一把 x 控制在[0,6]，因为 sigmoid 函数的值域为[0.5,1)， 2*simoid(x)-1 的值域为[0,1
	@Test
	public void calcself() throws Exception {
		List<User> userlist = userService.findAll();
		for (User user : userlist) {
			int mb = user.getMblogNum();
			int att = user.getAttnum();
			int fans =user.getFansNum();
			int zans = user.getZanNum();
			double a = 0.2 ;//微博
			double b = 0.2 ;//关注
			double c = 0.5 ;//粉丝
			double d = 0.1 ;//赞
			double self = a*mb+b*att+c*fans+d*zans;
			user.setSelfInf(String.valueOf(self));
			userService.update(user);
			System.out.println("==================");
		}
	}
	@Test
	public void guiyi() throws Exception {
		List<User> userlist = userService.findAll();
		for (User user : userlist) {
			double self = 2*sigmoid((Double.parseDouble(user.getSelfInf())) * 6.0) - 1 ;
			user.setSelfInf(String.valueOf(self));
			userService.update(user);
			System.out.println("==================");
		}
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
//	@Test
//	public void update() throws Exception{
//		List<ResultPR> datlist = resultPRService.findAll();
//		for (ResultPR resultPR : datlist) {
//			User u = userService.getById(resultPR.getId());
//			resultPR.setName(u.getName());
//			resultPRService.update(resultPR);
//		}
//		List<ResultCenter> datlist2 = resultCenterService.findAll();
//		for (ResultCenter resultPR : datlist2) {
//			User u = userService.getById(resultPR.getId());
//			resultPR.setName(u.getName());
//			resultCenterService.update(resultPR);
//		}
//		List<ResultMax> datlist3 = resultMaxService.findAll();
//		for (ResultMax resultPR : datlist3) {
//			User u = userService.getById(resultPR.getId());
//			resultPR.setName(u.getName());
//			resultMaxService.update(resultPR);
//		}
//		List<ResultMyPR> datlist4 = resultMyPRService.findAll();
//		for (ResultMyPR resultPR : datlist4) {
//			User u = userService.getById(resultPR.getId());
//			resultPR.setName(u.getName());
//			resultMyPRService.update(resultPR);
//		}
//	}
	@Test
	public void testInf() throws Exception{
		String result = "result/KS_RELATED/MDD_IB";
		String ori_result = "result/KS_RELATED/MDD";
		String ks_result = "result/KS_RELATED/ks2";
		String centerDist_result = "result/centerDist_result";
		String maxDegree_result = "result/maxDegree_result";
		//ks_result
		List<String> dataList = readToKS(ks_result);
		for (String data : dataList) {
			ResultKS my = new ResultKS();
			my.setId(data.split("\t")[0]);
			my.setValue(data.split("\t")[1]);
			User u = userService.getById(my.getId());
			my.setName(u.getName());
			my.setUser(u);
			resultKSService.save(my);
		}
		//result
//		List<String> dataList = readTo100(result);
//		for (String data : dataList) {
//			ResultMyPR my = new ResultMyPR();
//			my.setId(data.split("\t")[0]);
//			my.setValue(data.split("\t")[1]);
//			User u = userService.getById(my.getId());
//			my.setName(u.getName());
//			my.setUser(u);
//			resultMyPRService.save(my);
//		}
		//ori_result
//		List<String> dataList = readTo100(ori_result);
//		for (String data : dataList) {
//			ResultPR my = new ResultPR();
//			my.setId(data.split("\t")[0]);
//			my.setValue(data.split("\t")[1]);
//			User u = userService.getById(my.getId());
//			my.setName(u.getName());
//			my.setUser(u);
//			resultPRService.save(my);
//		}
		//centerDist_result
//		List<String> dataList = readTo100(centerDist_result);
//		for (String data : dataList) {
//			ResultCenter my = new ResultCenter();
//			my.setId(data.split("\t")[0]);
//			my.setValue(data.split("\t")[1]);
//			User u = userService.getById(my.getId());
//			my.setName(u.getName());
//			my.setUser(u);
//			resultCenterService.save(my);
//		}
		//maxDegree_result
//		List<String> dataList = readTo100(maxDegree_result);
//		for (String data : dataList) {
//			ResultMax my = new ResultMax();
//			my.setId(data.split("\t")[0]);
//			my.setValue(Integer.parseInt(data.split("\t")[1]));
//			User u = userService.getById(my.getId());
//			my.setName(u.getName());
//			my.setUser(u);
//			resultMaxService.save(my);
//		}
	}
	private List<String> readToKS(String ks_result) {
		List<String> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(ks_result));
			String str= null;
			while((str=br.readLine())!=null){
				int ks = Integer.parseInt(str.split("\t")[1]);
				if(ks>=6){
					list.add(str);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	private List<String> readTo100(String path) {
		List<String> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int index = 0 ;
			while((str=br.readLine())!=null){
				if(index==100)break;
				list.add(str);
				index++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Test
	public void followrelation() throws Exception{
		List<Repostrelation> repostrelationList = repostrelationService.findAll();
		List<Commentrelation> CommentrelationList = commentrelationService.findAll();
		Set<String> rela = new HashSet<>();
		for (Commentrelation commentrelation : CommentrelationList) {
			String muid = commentrelation.getMuid();
			String uid = commentrelation.getUid();
			rela.add(muid+":"+uid);
			
		}
		for (Repostrelation repostrelation : repostrelationList) {
			String muid = repostrelation.getMuid();
			String uid = repostrelation.getId().getUid();
			rela.add(muid+":"+uid);
		}
		System.out.println(rela.size());
		System.out.println("写入中。。。。");
	    File file = new File("data/follower");
	    FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (String s : rela) {
            	String muid = s.split(":")[0];
    			String uid = s.split(":")[1];
          	    writer.write(muid+"	"+uid);
          	    writer.newLine();
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("写入完毕。。。。");
			
//		for (String s : rela) {
//			System.out.println(s);
//			String muid = s.split(":")[0];
//			String uid = s.split(":")[1];
//			Userrelation userrelation = new Userrelation();
//			UserrelationId userrelationId = new UserrelationId();
//			userrelationId.setFromId(uid);
//			userrelationId.setToId(muid);
//			userrelation.setId(userrelationId);
//			userrelationService.save(userrelation);
//		}
	}
	/**
	 * 归一化函数
	 * @param x
	 * @return
	 */
	public static double sigmoid(double x) {
		double result =1/(1+Math.pow(Math.E,-x));
		return result;
	}
}
