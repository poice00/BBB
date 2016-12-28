package com.mb.ks;

import java.util.List;
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
		
		//2.u对邻居节点的影响因子之和
		Set<String> rnodes = Utils.getNodes(repost);
		List<String> redges = Utils.getEdges(repost);
		Set<String> cnodes = Utils.getNodes(comment);
		List<String> cedges = Utils.getEdges(comment);
		//3.u的MDD值
	}
}
