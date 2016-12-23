package com.mb.ks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

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
		Map<Integer, Set<String>> ksresult = new HashMap<>();
		//1.由ks先分层，算出每个节点所处的层数
		System.out.println(nodes.size());
		Map<String, Integer> degs = getDegree(edges,nodes);
		int index = 1;
		while(degs.size()!=0){
			Set<String> renodes = new HashSet<>();
			Map<String, Integer> kir = new HashMap<>();
			while(true){
				edges = removeEdges(edges, degs,index);
				nodes = removeNodes(nodes, degs, renodes,index);
//				System.out.println("移除后的节点大小: " + nodes.size());
//				System.out.println("移除后的边的大 小: " + edges.size());
				degs = getDegree(edges,nodes,kir);
				boolean flag = true;
				for (String node : degs.keySet()) {
					if(degs.get(node)<=index) flag = false;
				}
				if(flag){
					break;
				}
			}
			print(kir);//移除后节点剩下的度
//			System.out.println(index + "-shell: " + renodes.size());
			ksresult.put(index, renodes);
			index ++;
//			System.out.println("===========index:======================= "+index);
		}
		//2.由MDD算出每个节点的k-shell值
//		for (Integer key : ksresult.keySet()) {
//			System.out.println(key + ": " + ksresult.get(key));
//		}
	}

	private static Set<String> removeNodes(Set<String> nodes, Map<String, Integer> degs, Set<String> renodes, int index) {
		for (String node : degs.keySet()) {//移除节点
			if(degs.get(node)<=index) {
				nodes.remove(node);
				renodes.add(node);
			}
		}
		return nodes;
	}

	private static List<String> removeEdges(List<String> edges, Map<String, Integer> degs, int index) {
		for (int i = 0;i<edges.size();i++) {//移除边
			String from = edges.get(i).split("\t")[0];
			String to = edges.get(i).split("\t")[1];
			if(degs.get(from)<=index||degs.get(to)<=index) {
				edges.remove(i);
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

	private static Map<String, Integer> getDegree(List<String> edges, Set<String> nodes) {
		Map<String, Integer> degs = new HashMap<>();
		for (String edge : edges) {
			String from = edge.split("\t")[0];
			String to = edge.split("\t")[1];
			if(degs.get(from)==null) degs.put(from, 1);
			else degs.put(from, degs.get(from)+1);
			if(degs.get(to)==null) degs.put(to, 1);
			else degs.put(to, degs.get(to)+1);
		}
		return degs;
	}
	private static Map<String, Integer> getDegree(List<String> edges, Set<String> nodes, Map<String, Integer> kir) {
		Map<String, Integer> degs = new HashMap<>();
		for (String edge : edges) {
			String from = edge.split("\t")[0];
			String to = edge.split("\t")[1];
			if(degs.get(from)==null) degs.put(from, 1);
			else degs.put(from, degs.get(from)+1);
			if(degs.get(to)==null) degs.put(to, 1);
			else degs.put(to, degs.get(to)+1);
		}
		kir.putAll(degs);
//		print(degs);
		return degs;
	}
}
