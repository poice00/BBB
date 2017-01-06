package com.mb.ks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mb.preDeal.Util;

/*
 * -Shell 
分解算法分解步骤如下：
	1.假设网络中不存在度为 0 的节点，即每个节点度值 k1 ，先将网络中所有度值为 1 的节点剥落，剥落所有度值为 1 的节点后，生成的新网络中会继续有度值为 1 
的节点，重复之前的步骤，继续进行剥落度值为 1 的节点，直到新网络中所有的节点的度值 k2，把所有剥落的节点称为网络的 1-Shell（1-壳）；
	2.新网络中节点的度值有 k2，同第一步相同，重复剥落度值为 2 的节点，直到网络中所有节点的度值 k3，将这一步剥落的节点称为网络的 2-Shell。
	以此类推，进一步得到网络的 3-Shell、4-Shell 等更高的 Shell 层，直到网络中的所有的节点都被剥落，这样网络中的每个节点都有对应的 K-Shell 
值ks，例如属于 2-Shell 的节点，其 2ks ，对于属于ks-Shell 层的节点都有度值ks 
 
 */
public class Ks {
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
		Map<String, Integer> degs = getDegree(edges,nodes);
		//2.递归移除所有度为1的节点 及其相关的边
		int index = 1;
		while(degs.size()!=0){
			Set<String> renodes = new HashSet<>();
			while(true){
				edges = removeEdges(edges, degs,index);
				nodes = removeNodes(nodes, degs, renodes,index);
				System.out.println("移除后的节点大小: " + nodes.size());
				System.out.println("移除后的边的大 小: " + edges.size());
				//3.统计移除后节点的度的信息
				degs = getDegree(edges,nodes);
				boolean flag = true;
				for (String node : degs.keySet()) {
					if(degs.get(node)<=index) flag = false;
				}
				if(flag){
//					print(degs);
					break;
				}
			}
//			print(degs);
//			System.out.println(index + "-shell: " + renodes.toString());
			Utils.writerresultTo("result/KS_RELATED/ks2", index ,renodes);
			System.out.println(index + "-shell: " + renodes.size());
			index ++;
			System.out.println("===========index:======================= "+index);
		}
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
}
