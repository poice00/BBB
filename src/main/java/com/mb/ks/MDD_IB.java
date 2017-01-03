package com.mb.ks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MDD_IB {
	
	public static void main(String[] args) {
		//sina #西安身边事#
		String self = "data/self"; //转发
		String repost = "data/repost.txt"; //转发
		String comment = "data/comment.txt";//评论
		String follower = "data/follower";//关注
		String user = "data/user.txt";//用户
		String MDD = "result/KS_RELATED/MDD";
		String reactionUser = "result/KS_RELATED/reactionUser";
		//u的MDD值 乘以 u对邻居的影响因子之和 
		//1.u自身的属性
//		Map<String, Double> selfMap =Utils.readData(self);
		//2.u对邻居节点的影响因子之和
//		Map<String, Double> neibMap = new HashMap<>();
//		Set<String> rnodes = Utils.getNodes(repost);
//		List<String> redges = Utils.getEdges(repost);//评论
//		Set<String> cnodes = Utils.getNodes(comment);//转发
//		List<String> cedges = Utils.getEdges(comment);
//		List<String> userList =Utils.readDataToList(user);
		//考虑节点u对其他节点的交互次数
//		for (String u : userList) {
//			//1.计算节点u在转发和评论中与其他节点交互次数
//			System.out.println("================="+ u +"=================");
//			double inf = getInractionInf(u,redges,cedges);
//			neibMap.put(u, inf);
//		}
//		Utils.writerDOUBLEresultTo("result/KS_RELATED/reactionUser", neibMap);
		Map<String, Double> neibMap =Utils.readData(reactionUser);
		print(neibMap);
		//3.u的MDD值
//		Map<String, Double> mddMap =Utils.readData(MDD);
	}
	//计算节点在两个网络中的影响力
	private static double getInractionInf(String user, List<String> redges, List<String> cedges) {
		// TODO Auto-generated method stub
		int index = 0 ;//
		Map<String, Integer> mapr = new HashMap<>();
		//1.在转发网络中的影响力
		for (String redge : redges) {
			if(redge.split("\t")[0].equals(user)){
				if(mapr.get(redge)==null) mapr.put(redge, 1);
				else mapr.put(redge, mapr.get(redge)+1);
			}
		}
		//2.在评论网络中的影响力
		for (String cedge : cedges) {
			if(cedge.split("\t")[0].equals(user)){
				if(mapr.get(cedge)==null) mapr.put(cedge, 1);
				else mapr.put(cedge, mapr.get(cedge)+1);
			}
		}
		//3.是否有关注关系
		for (String key : mapr.keySet()) {
			if(key.split("\t")[0].equals(user)){
				index += mapr.get(key);
			}
		}
		return index;
	}
	//得到邻居节点
	private static Set<String> getNeibour(List<String> redges, List<String> cedges, String u) {
		// TODO Auto-generated method stub
		return null;
	}
	private static void print(Map<String, Double> mddMap) {
		for (String s : mddMap.keySet()) {
			System.out.println(s + "-" + mddMap.get(s));
		}
	}
}
