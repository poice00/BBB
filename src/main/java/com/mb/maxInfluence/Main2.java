package com.mb.maxInfluence;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.SimpleFormatter;
/**
 * 2016/06/27 09:47:04
 * System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
 * KK贪心算法
 * @author ssy
 *
 */
public class Main2 {
	public static void main(String[] args) {
		List<String> datalist = Utils.getListDatas("D:/eclipse_workspace/BBB/data/Wiki-Vote.txt");//7115节点 103689
		Set<String> nodeSet = Utils.getListNodes("D:/eclipse_workspace/BBB/data/Wiki-Vote.txt");
		System.out.println(nodeSet.size());
		int k = 5 ; //种子节点k
		Set<String> seedsSet = new TreeSet<String>();
		Set<String> Nodes = null;
		for (int i = 0; i < k; i++) {
			long start = System.currentTimeMillis();
			System.out.print("第" + (i+1) +"次节点选择: ");
			String nodeFinal = null;
			int maxSize = 0;
			for (String node : nodeSet) {//遍历每个节点,加入中自己和，计算边际影响值
				if(seedsSet.size()==0){
					Set<String> activeNodes = getActiveNodes(datalist, node);
					if(activeNodes.size()>maxSize){
						maxSize = activeNodes.size();
						Nodes = activeNodes;
						nodeFinal = node ;
					}
				}else{
//					Set<String> activeseedsNodes = getActiveNodes(datalist, seedsSet);
					int prevSize =  Nodes.size();
					Set<String> activeNodes = getActiveNodes(datalist, Nodes,node);
					int size = activeNodes.size() -  prevSize;
					if(size>maxSize){
						maxSize = size;
						nodeFinal = node ;
						Nodes = activeNodes;
					};
				}
			}
			seedsSet.add(nodeFinal);
			System.out.println("nodeFinal: " + nodeFinal + " 节点集合大小： " + Nodes.size());
//			System.out.println("maxSize: " + maxSize);
			System.out.println(Nodes);
			long end = System.currentTimeMillis();
			System.out.println("======节点选择完毕======,耗时：" + (end- start) + "ms");
		}
		
	}

	private static Set<String> getActiveNodes(List<String> datalist,
		Set<String> activeseedsNodes, String node) {
		Set<String> activeNode = new TreeSet<String>();
		activeNode.addAll(activeseedsNodes);
		while(node!=null){
			double max = 0.1;
			String maxNode = null;
			for (String data : datalist) {
				if(data.split("\t")[0].equals(node) && !activeNode.contains(node)){
					if(Double.parseDouble(data.split("\t")[2]) >= max){
						max = Double.parseDouble(data.split("\t")[2]);
						maxNode =data ;
					}
				}
			}
			if(maxNode == null) node = null; 
			else{
				activeNode.add(node);
				node = maxNode.split("\t")[1];
			}
		}
//		System.out.println(activeNode);
		return activeNode;
	}


	public static Set<String> getActiveNodes(List<String> datalist, String node) {
		Set<String> activeNode = new TreeSet<String>();
		while(node!=null){
//			System.out.println(node);
			double max = 0.1;
			String maxNode = null;
			for (String data : datalist) {
				if(data.split("\t")[0].equals(node) && !activeNode.contains(node)){
					if(Double.parseDouble(data.split("\t")[2]) >= max){
						max = Double.parseDouble(data.split("\t")[2]);
						maxNode =data ;
					}
				}
			}
			if(maxNode == null) node = null; 
			else{
				activeNode.add(node);
				node = maxNode.split("\t")[1];
			}
//			System.out.println("xx: " + maxNode);
		}
//		System.out.println(activeNode);
		return activeNode;
	}
}
