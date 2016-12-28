package com.mb.ks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

public class MDD {
	public static void main(String[] args) {
		//sina #西安身边事#
		String repost = "data/repost.txt"; //转发
		String comment = "data/comment.txt";//评论
		String follower = "data/follower";//关注
		String user = "data/user.txt";//用户
		String exam2 = "data/exam2";//用户
		Set<String> nodes = Utils.getNodes(follower);
		List<String> edges = Utils.getEdges(follower);
		Map<String, Float> degs = getDegree(edges,nodes);
		Map<String, Float> mddresult = new LinkedHashMap<>();//存放MDD结果
		Map<String, Float> mddresult2 = new LinkedHashMap<>();//存放MDD结果
		//由MDD算出每个节点的k-shell值
		//2.移除最小的KM知道节点，赋值为M 参数：nodes2 edges2 ksresult(<8,<5252701469,8.0>>)
//		System.out.println(" - - "+degs.toString());
		while(degs.size()!=0){
			mddresult.putAll(degs);
			//1.选出最小的节点集合
			Set<String> minNodes = new HashSet<>();
			//1.1 选出最小的一个
			float min = Float.MAX_VALUE;
			for (String node : degs.keySet()) {
				if(degs.get(node)<min) 
					min = degs.get(node) ;
			}
			//1.1 选出最小的集合
			for (String node : degs.keySet()) {
				if(degs.get(node)==min)
					minNodes.add(node);
			}
			System.out.println("最小的节点的集合： " + minNodes.size());
//			System.out.println("剩余的节点; "+ minNodes.toString());
			//2.用K(i)m=K(i)r+K(i)e更新剩余的节点的值
			//更新节点的值
			for (String edge : edges) {
				String from = edge.split("\t")[0];
				String to = edge.split("\t")[1];
				BigDecimal flag = new BigDecimal(Float.toString(0.3f));
				if(minNodes.contains(from)&&!minNodes.contains(to)){
					BigDecimal bto = new BigDecimal(Float.toString(degs.get(to)));
					degs.put(to, bto.subtract(flag).floatValue());
				}
				if(minNodes.contains(to)&&!minNodes.contains(from)){
					BigDecimal bfrom = new BigDecimal(Float.toString(degs.get(from)));
					degs.put(from, bfrom.subtract(flag).floatValue());;
				}
					
			}
			//3.移除最小的节点集合
			edges = removeEdges(edges, minNodes);
			nodes = removeNodes(nodes, minNodes);
			for (String minnode : minNodes) {
				degs.remove(minnode);
			}
//			System.out.println("移除节点的值- - "+degs.toString());
		}
//		System.out.println("最终节点的MDD值- - "+mddresult.toString());
		//实现排序
		List<Map.Entry<String,Float>> list = new ArrayList<Map.Entry<String,Float>>(mddresult.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,Float>>() {
            //升序排序
            public int compare(Entry<String, Float> o1,
                    Entry<String, Float> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println(list);
        for (int i = mddresult.size()-1; i>0; i--) {
        	mddresult2.put(list.get(i).getKey(), list.get(i).getValue());
		}
		Utils.writerresultTo("result/KS_RELATED/MDD", mddresult2);
	}


	private static Set<String> removeNodes(Set<String> nodes, Set<String> minNodes) {
		for (String node : minNodes) {//移除节点
				nodes.remove(node);
		}
		return nodes;
	}

	private static List<String> removeEdges(List<String> edges, Set<String> minNodes) {
		for (int i = 0;i<edges.size();i++) {//移除边
			String from = edges.get(i).split("\t")[0];
			String to = edges.get(i).split("\t")[1];
			for (String node : minNodes) {//移除节点
				if(from.equals(node)||to.equals(node)) {
					edges.remove(i);
					i--;
					break;
				}
			}
		}
		
		return edges;
	}




	private static Map<String, Float> getDegree(List<String> edges, Set<String> nodes) {
		Map<String, Float> degs = new HashMap<>();
		for (String edge : edges) {
			String from = edge.split("\t")[0];
			String to = edge.split("\t")[1];
			if(degs.get(from)==null) degs.put(from, 1.0f);
			else degs.put(from, degs.get(from)+1.0f);
			if(degs.get(to)==null) degs.put(to, 1.0f);
			else degs.put(to, degs.get(to)+1.0f);
		}
		return degs;
	}
}
