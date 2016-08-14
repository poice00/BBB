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
	
	
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sf);
		
	}
}
