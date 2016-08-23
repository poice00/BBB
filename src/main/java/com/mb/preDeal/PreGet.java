//package com.mb.preDeal;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.apache.tomcat.util.digester.SetRootRule;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//
//public class PreGet {
//	static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//	static SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
//	static Session session=null;
//	static Transaction tx=null;
//	public void open(){
//		session = sf.openSession();
//		tx = session.beginTransaction();
//	}
//	public void close(){
//		tx.commit();
//		session.close();
//		ctx.close();
//	}
//	public List<Topic> writerList(){
//		TopicService topicService = (TopicService) ctx.getBean("topicServiceImpl");
//		List<Topic> topicList = topicService.findAll();
//		return topicList;
//	}
//	public List<Relation> relaList(){
//		RelationService relationService = (RelationService) ctx.getBean("relationServiceImpl");
//		List<Relation> relaList = relationService.findAll();
//		return relaList;
//	}
//	public static void main(String[] args) {
//	
//		long start = System.currentTimeMillis();
//		//处理评论里面的关系
////		dealComment();
//		//把带有@的评论提取出来
////		extractComment();
//		//对提取出来的评论进行处理
////		dealCommTmp();
//		//把回复关系保存
////		saveTotmp();
//		//写入文件
////		writerTo();
//		//提取节点
////		extractNode();
//		//计算每个节点的影响力
////		calcInf();
//		//选择前20 的
////		UsertmpService usertmpService = (UsertmpService) ctx.getBean("usertmpServiceImpl");
////		List<Usertmp> usertmpList = usertmpService.findTop(20);
////		for (Usertmp usertmp : usertmpList) {
////			System.out.println(usertmp.getUsername() + " :" + usertmp.getInflu());
////		}
////		System.out.println(usertmpList.size());
//		Util.getData("C:\\Users\\Administrator\\Desktop\\data.txt");
//		System.out.println(System.currentTimeMillis() - start);
//		
//		
////		List<Relation> relaList =ad.relaList();
////		Set<String> relaSet = new HashSet<String>();
////		for (Relation relation : relaList) {
////			String rela = relation.getUserFrom() + "," + relation.getUserTo();
////			relaSet.add(rela);
////		}
////		System.out.println("共有边:" + relaSet.size() + "条");
////		
////		Set<String> resultRela = new HashSet<>();
////		for (Relation relation : relaList) {
////			if(useNameSet.contains(relation.getUserFrom())&&useNameSet.contains(relation.getUserTo())){
////				resultRela.add(relation.getUserFrom()+"," + relation.getUserTo());
////			}
////		}
////		System.out.println("4月份发帖的用户共有边:" + resultRela.size() + "条");
////		Set<String> relaNode = new HashSet<String>();
////		for (String string : resultRela) {
////			String []node = string.split(",");
////			relaNode.add(node[0]);
////			relaNode.add(node[1]);
////		}
////		System.out.println("4月份发帖的有边用户:" + relaNode.size() + "个");
////		
////		writerTofile(resultRela,"E:\\ssyworkspace\\AAA\\Data\\data.txt");
//    }
//	private static void calcInf() {
//		UsertmpService usertmpService = (UsertmpService) ctx.getBean("usertmpServiceImpl");
//		List<Usertmp> usertmpList = usertmpService.findAll();
//		//g1取0.05，g2取0.25，g3取0.3，g4取0.4
//		// @param g1 浏览次数权值
//		// @param g2 作者发表文章数权值
//		// @param g3 文章被回复数权值
//		// @param g4 粉丝数权值
//		// @param g5 N权值
//		double g1 = 0.35;
//		double g2 = 0.35;
//		double g3 = 0.65;
//		double g4 = 0;
//		double g5 = 0.65;
//		List<Double> N1List = new ArrayList<Double>();
//		List<Double> NList = new ArrayList<Double>();
//		for (Usertmp usertmp : usertmpList) {
//			double N = Util.calfluencePre(Double.parseDouble(usertmp.getPostCount()), 
//					Double.parseDouble(usertmp.getOthersReplyMeCount()), 
//					Double.parseDouble(usertmp.getFansCount()), g2, g3, g4);
//			NList.add(N);
//			N1List.add((Double.parseDouble(usertmp.getScanCount())));
//		}
//		for (Usertmp usertmp : usertmpList) {
//			double influence =  Util.calfluence(Double.parseDouble(usertmp.getScanCount()), 
//					Double.parseDouble(usertmp.getPostCount()), 
//					Double.parseDouble(usertmp.getOthersReplyMeCount()),
//					Double.parseDouble(usertmp.getFansCount()), g1, g2, g3, g4, g5, N1List, NList);
//			System.out.println("inf: " + influence);
//			Usertmp tmp = usertmpService.getById(usertmp.getUserId());
//			tmp.setInflu(influence);
//			usertmpService.update(tmp);
//		}
//	}
//	public static void extractNode() {
//		TopicService topicService = (TopicService) ctx.getBean("topicServiceImpl");
//		CommentService commentService = (CommentService) ctx.getBean("commentServiceImpl");
//		UsertmpService usertmpService = (UsertmpService) ctx.getBean("usertmpServiceImpl");
//		List<Topic> topicList = topicService.findAll();
//		Set<User> useSet = new HashSet<User>();
//		for (Topic topic : topicList) {
//			useSet.add(topic.getUser());
//		}
//		System.out.println("4月份发帖的用户：" + useSet.size());
//		for (User user : useSet) {
//			//计算发帖数目
//			List<Topic> postTopicList = topicService.getByUserId(user.getUserId());
//			int postCount  = postTopicList.size();
//			int OthersReplyMeCount = 0 ;//计算别人回复我的数目
//			int scanCount = 0;//计算浏览的总次数
//			if(postCount!=0){
//				for (Topic topic : postTopicList) {
//					OthersReplyMeCount += Integer.parseInt(topic.getReplyNums());
//					scanCount += Integer.parseInt(topic.getScanNums());
//				}
//			}
//			int replyOtherCount= 0 ;//计算我参与回复的数目
//			List<Comment> postCommentList = commentService.getByUserId(user.getUserId());
//			replyOtherCount += postCommentList.size();
//			Usertmp tmp = new Usertmp();
//			tmp.setFansCount(user.getFansCount());
//			tmp.setUserId(user.getUserId());
//			tmp.setUsername(user.getName());
//			//
//			tmp.setOthersReplyMeCount(String.valueOf(OthersReplyMeCount));
//			tmp.setPostCount(String.valueOf(postCount));
//			tmp.setReplyOtherCount(String.valueOf(replyOtherCount));
//			tmp.setScanCount(String.valueOf(scanCount));
//			usertmpService.save(tmp);
//		}
//	}
//	public static void writerTo() {
//		TopicService topicService = (TopicService) ctx.getBean("topicServiceImpl");
//		CommentService commentService = (CommentService) ctx.getBean("commentServiceImpl");
//		UsertmpService usertmpService = (UsertmpService) ctx.getBean("usertmpServiceImpl");
//		RelationtmpService relationtmpService = (RelationtmpService) ctx.getBean("relationtmpServiceImpl");
//		UserService userService = (UserService) ctx.getBean("userServiceImpl");
//		List<Relationtmp> relationtmpList = relationtmpService.findAll();
//		List<User> userList = userService.findAll();
//		Set<String> useSet = new HashSet<String>();
//		for (User user : userList) {
//			useSet.add(user.getName());
//		}
//		System.out.println("userSet : " + useSet.size() );
//		List<String> resultSet = new ArrayList<String>();
//		Set<String> userrIdSet = new HashSet<>();
//		for (Relationtmp relationtmp : relationtmpList) {
//			if(useSet.contains(relationtmp.getUserFrom())&&useSet.contains(relationtmp.getUserTo())){
//				User u1 = userService.getByName(relationtmp.getUserFrom());
//				User u2 = userService.getByName(relationtmp.getUserTo());
//				userrIdSet.add(u1.getUserId());
//				userrIdSet.add(u2.getUserId());
//				resultSet.add(relationtmp.getUserFrom() + "," + relationtmp.getUserTo());
//				System.out.println(u1.getUserId()+ "-" +u2.getUserId());
//			}
//		}
//		System.out.println("userIdSize: " + userrIdSet.size());
//		for (String useId : userrIdSet) {
//			User user = userService.getById(useId);
//			System.out.println(user.getName());
//			List<Topic> postTopicList = topicService.getByUserId(user.getUserId());
//			int postCount  = postTopicList.size();
//			int OthersReplyMeCount = 0 ;//计算别人回复我的数目
//			int scanCount = 0;//计算浏览的总次数
//			if(postCount!=0){
//				for (Topic topic : postTopicList) {
//					OthersReplyMeCount += Integer.parseInt(topic.getReplyNums());
//					scanCount += Integer.parseInt(topic.getScanNums());
//				}
//			}
//			int replyOtherCount= 0 ;//计算我参与回复的数目
//			List<Comment> postCommentList = commentService.getByUserId(user.getUserId());
//			replyOtherCount += postCommentList.size();
//			Usertmp tmp = new Usertmp();
//			tmp.setFansCount(user.getFansCount());
//			tmp.setUserId(user.getUserId());
//			tmp.setUsername(user.getName());
//			//
//			tmp.setOthersReplyMeCount(String.valueOf(OthersReplyMeCount));
//			tmp.setPostCount(String.valueOf(postCount));
//			tmp.setReplyOtherCount(String.valueOf(replyOtherCount));
//			tmp.setScanCount(String.valueOf(scanCount));
//			usertmpService.save(tmp);
//		}
//		System.out.println("resultSet: " + resultSet.size());
////		writerTofile(resultSet,"E:\\ssyworkspace\\AAA\\Data\\data.txt");
//	}
//	private static void saveTotmp() {
//		CommentService commentService = (CommentService) ctx.getBean("commentServiceImpl");
//		RelationtmpService relationtmpService = (RelationtmpService) ctx.getBean("relationtmpServiceImpl");
//		CommenttmpService commenttmpService = (CommenttmpService) ctx.getBean("commenttmpServiceImpl");
//		List<Comment> commentList = commentService.findAll();
//		List<Commenttmp> CommenttmpList = commenttmpService.findAll();
//		for (Comment comment : commentList) {
//			if(!comment.getCommentFrom().equals(comment.getCommentTo())){
//				System.out.println("111111111");
//				Relationtmp tmp = new Relationtmp();
//				tmp.setUserFrom(comment.getCommentFrom());
//				tmp.setUserTo(comment.getCommentTo());
//				relationtmpService.save(tmp);
//			}
//		}
//		for (Commenttmp commenttmp : CommenttmpList) {
//			if(!commenttmp.getCommentFrom().equals(commenttmp.getCommentTo())){
//				System.out.println("2222222222");
//				Relationtmp tmp = new Relationtmp();
//				tmp.setUserFrom(commenttmp.getCommentFrom());
//				tmp.setUserTo(commenttmp.getCommentTo());
//				relationtmpService.save(tmp);
//			}
//		}
//	}
//	private static void dealComment() {
//		CommentService commentService = (CommentService) ctx.getBean("commentServiceImpl");
//		List<Comment> commentList = commentService.findAll();
//		for (Comment comment : commentList) {
//			System.out.println(comment.getCommentId());
//			Comment commentTmp = commentService.getById(comment.getCommentId());
//			commentTmp.setCommentFrom(comment.getUser().getName());
//			commentTmp.setCommentTo(comment.getTopic().getUser().getName());
//			commentService.update(commentTmp);
//		}
//		
//	}
//	public static void dealCommTmp() {
//		CommenttmpService commenttmpService = (CommenttmpService) ctx.getBean("commenttmpServiceImpl");
//		List<Commenttmp> commenttmpList = commenttmpService.findAll();
//		for (Commenttmp commenttmp : commenttmpList) {
////			String contnent = commenttmp.getContent();
////			int count = calcSpS(commenttmp.getContent());
////			Commenttmp tmp = commenttmpService.getById(commenttmp.getCommentId());
////			if(count == 1){//@一个用户的
////				if(contnent.contains("<br>")){
////					String []result = contnent.split(" ")[0].split("<br>");
////					if(result.length>1){
////						//更新数据库
////						tmp.setCommentTo(result[0]);
////						tmp.setContenttmp(result[1]);
////						System.out.println(result[0] + "----" + result[1]);
////						
////					}else{
////						tmp.setCommentTo(result[0]);
////						System.out.println(result[0]);
////					}
////				}else{
////					tmp.setCommentTo(contnent.split(" ")[0]);
////					System.out.println(contnent.split(" ")[0]);
////				}
////				commenttmpService.update(tmp);
////			}else{//@多个用户的
////				tmp.setCommentTo(contnent.split(" ")[0]);
////				System.out.println(contnent.split(" ")[0]);
////				commenttmpService.update(tmp);
////			}
//			//评论对象的处理
//			Commenttmp tmp = commenttmpService.getById(commenttmp.getCommentId());
////			if(commenttmp.getCommentTo().startsWith("@")){
////				System.out.println("commemtTo: " + commenttmp.getCommentTo());
////				String contnent = commenttmp.getCommentTo().substring(1);
////				tmp.setCommentTo(contnent);
////				commenttmpService.update(tmp);
////			}
//			//评论作者的处理
////			String name = commenttmp.getUser().getName();
////			System.out.println(name);
////			tmp.setCommentFrom(name);
////			commenttmpService.update(tmp);
//			
//			if(commenttmp.getCommentTo().equals("")){
//				String name = commenttmp.getTopic().getUser().getName();
//				System.out.println(name);
//				tmp.setCommentTo(name);
//				commenttmpService.update(tmp);
//			}
//			
//		}
//	}
//	private static int calcSpS(String content) {
//		int count = 0 ;
//		for (int i = 0; i < content.length(); i++) {
//			if('@'==content.charAt(i)){
//				count ++;
//			}
//		}
//		return count;
//	}
//	public static void extractComment() {
//		CommentService commentService = (CommentService) ctx.getBean("commentServiceImpl");
//		CommenttmpService commenttmpService = (CommenttmpService) ctx.getBean("commenttmpServiceImpl");
//		List<Comment> commentList = commentService.findCondition("@");
//		int i = 1 ;
//		for (Comment comment : commentList) {
//				System.out.println("===" + i + "===");
//				Commenttmp tmp = new Commenttmp();
//				tmp.setCommentId(comment.getCommentId());
//				tmp.setContent(comment.getContent());
//				tmp.setGrabTime(comment.getGrabTime());
//				tmp.setPostTime(comment.getPostTime());
//				tmp.setTopic(comment.getTopic());
//				tmp.setUser(comment.getUser());
//				commenttmpService.save(tmp);
//				//从原来的comment表中删除
//				commentService.delete(comment.getCommentId());
//				i ++;
//			}
//		System.out.println(commentList.size());
//	}
//	public static void writerTofile(Collection<String> resultRela,String path) {
//		File file = new File(path);
//		 FileWriter fw = null;
//        BufferedWriter writer = null;
//        try {
//            fw = new FileWriter(file);
//            writer = new BufferedWriter(fw);
//            for(String s : resultRela){
//                writer.write(s);
//                writer.newLine();//换行
//            }
//            writer.flush();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                writer.close();
//                fw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//	}
//	public static double getInflu(String name) {
//		UsertmpService usertmpService = (UsertmpService) ctx.getBean("usertmpServiceImpl");
//		Usertmp u = usertmpService.getByName(name);
//		return u.getInflu();
//	}
//}
