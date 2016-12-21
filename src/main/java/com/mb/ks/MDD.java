package com.mb.ks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MDD {
	public static void main(String[] args) {
		//sina #西安身边事#
		String repost = "data/repost.txt"; //转发
		String comment = "data/comment.txt";//评论
		String follower = "data/follower";//关注
		String user = "data/user.txt";//用户
		String exam = "data/exam";//用户
		Set<String> nodes = Utils.getNodes(follower);
		List<String> edges = Utils.getEdges(follower);
		//1.先统计处每个节点的度，循环去除度为1的  知道所有节点的度都大于等于2
		System.out.println(nodes.size());
		//1.统计节点的度的信息
		Map<String, Double> degs = getDegree(edges,nodes);
		//2.递归移除所有度为1的节点 及其相关的边
		int index = 1;
		while(degs.size()!=0){
			Set<String> renodes = new HashSet<>();
			List<String> reedges = new ArrayList<>();
			while(true){
				edges = removeEdges(edges, degs,reedges,index);
				nodes = removeNodes(nodes, degs, renodes,index);
				System.out.println("移除后的节点大小: " + nodes.size());
				System.out.println("移除后的边的大 小: " + edges.size());
				//3.更新移除后节点的度的信息
//				degs = getDegree(edges,nodes);
				degs = getDegree(edges,nodes,reedges);
				boolean flag = true;
				for (String node : degs.keySet()) {
					if(degs.get(node)<=index) flag = false;
				}
				if(flag){
//							print(degs);
					break;
				}
			}
//					print(degs);
//					System.out.println(index + "-shell: " + renodes.toString());
//			Utils.writerresultTo("result/KS_RELATED/ks", index + "-shell: " + renodes.toString());
			index ++;
			System.out.println("===========index:======================= "+index);
		}
	}

	private static Set<String> removeNodes(Set<String> nodes, Map<String, Double> degs, Set<String> renodes, int index) {
		for (String node : degs.keySet()) {//移除节点
			if(degs.get(node)<=index) {
				nodes.remove(node);
				renodes.add(node);
			}
		}
		return nodes;
	}

	private static List<String> removeEdges(List<String> edges, Map<String, Double> degs, List<String> reedges, int index) {
		for (int i = 0;i<edges.size();i++) {//移除边
			String from = edges.get(i).split("\t")[0];
			String to = edges.get(i).split("\t")[1];
			if(degs.get(from)<=index||degs.get(to)<=index) {
				edges.remove(i);
				reedges.add(from+"\t"+to);
				i--;
			}
		}
		return edges;
	}

	private static void print(Map<String, Integer> degs) {
		for (String node : degs.keySet()) {
				System.out.println(node + " -- " + degs.get(node));
		}
	}
	private static Map<String, Double> getDegree(List<String> edges, Set<String> nodes, List<String>  reedges) {
		Map<String, Double> degs = new HashMap<>();
		//移除之后更新剩余节点的度
		for (String edge : edges) {
			String from = edge.split("\t")[0];
			String to = edge.split("\t")[1];
			if(degs.get(from)==null) degs.put(from, 1.0);
			else degs.put(from, degs.get(from)+1);
			if(degs.get(to)==null) degs.put(to, 1.0);
			else degs.put(to, degs.get(to)+1.0);
		}
		//Km=K(i)r+λK(i)e λ取值为0.7
		for (String node : nodes) {
			for (String edge : reedges) {
				if(edge.contains(node)){
					degs.put(node, degs.get(node) + 0.7);
				}
			}
		}
		
		return degs;
	}

	private static Map<String, Double> getDegree(List<String> edges, Set<String> nodes) {
		Map<String, Double> degs = new HashMap<>();
		for (String edge : edges) {
			String from = edge.split("\t")[0];
			String to = edge.split("\t")[1];
			if(degs.get(from)==null) degs.put(from, 1.0);
			else degs.put(from, degs.get(from)+1.0);
			if(degs.get(to)==null) degs.put(to, 1.0);
			else degs.put(to, degs.get(to)+1);
		}
		return degs;
	}
}
