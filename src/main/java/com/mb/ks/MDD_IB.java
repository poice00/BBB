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
		//u的MDD值 乘以 u对邻居的影响因子之和 
		//1.u自身的属性
		Map<String, Double> selfMap =Utils.readData(self);
		//2.u对邻居节点的影响因子之和
		Map<String, Double> neibMap = new HashMap<>();
		Set<String> rnodes = Utils.getNodes(repost);
		List<String> redges = Utils.getEdges(repost);//评论
		Set<String> cnodes = Utils.getNodes(comment);//转发
		List<String> cedges = Utils.getEdges(comment);
		List<String> userList =Utils.readDataToList(user);
		for (String u : userList) {
			//1.取得u的邻居节点集合
			Set<String> neibour = getNeibour(redges,cedges,u);
			//2.遍历u的邻居节点
			double relaInf = 0 ;//统计邻居节点的交互影响总合
			for (String userneibour : neibour) {
				double inf = getInf(userneibour,redges,cedges);
				relaInf += inf;
			}
			neibMap.put(u, relaInf);
		}
		//3.u的MDD值
	}

	private static double getInf(String userneibour, List<String> redges, List<String> cedges) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static Set<String> getNeibour(List<String> redges, List<String> cedges, String u) {
		// TODO Auto-generated method stub
		return null;
	}
}
