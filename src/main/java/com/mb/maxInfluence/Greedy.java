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
public class Greedy {
	public static void main(String[] args) {
		List<String> datalist = Utils.getListDatas("data/Wiki-Vote.txt");//7115节点 103689
		Set<String> nodeSet = Utils.getListNodes("data/Wiki-Vote.txt");
		int k = 30; //种子节点k
		Set<String> seedsSet = new TreeSet<String>();//种子节点的集合
		Set<String> Nodes = new TreeSet<String>();//激活的节点
		long begin = System.currentTimeMillis();
		for (int i = 0; i < k; i++) {
			long start = System.currentTimeMillis();
			System.out.print("第" + (i+1) +"次节点选择: ");
			String nodeFinal = null;
			Set<String> tmpActivedNodes = null ;//
			int maxSize = 0;
			for (String node : nodeSet) {//遍历每个节点,加入中自己和，计算边际影响值
				if(!Nodes.contains(node)){ //如果当前节点是未激活状态
					Set<String> activeNodes = null;
//					if(seedsSet.size()==0)//第一次选择节点
//						activeNodes = getActiveNodes(datalist, node);
//					else 
					activeNodes = getActiveNodes(datalist, Nodes,node);//存放当前节点激活的节点
					int prevSize =  Nodes.size();
					int size = activeNodes.size() -  prevSize;
					if(size>maxSize){
						maxSize = size;
						nodeFinal = node ;
						tmpActivedNodes = activeNodes;
					};
				}
			}
			Nodes.addAll(tmpActivedNodes);
			seedsSet.add(nodeFinal);
			System.out.println("nodeFinal: " + nodeFinal + " 节点集合大小： " + Nodes.size());
			long end = System.currentTimeMillis();
			System.out.println("======节点选择完毕======,耗时：" + (end- start) + "ms");
		}
		long over = System.currentTimeMillis();
		System.out.println("原始节点大小： " + nodeSet.size());
		System.out.println("最终选取的节点是： " + seedsSet.toString());
		System.out.println("已激活的节点大小： " + Nodes.size());
		System.out.println("已激活的节点： " + Nodes);
		System.out.println("======程序运行完毕======,总耗时：" + (over- begin) + "ms");
		
	}

	private static Set<String> getActiveNodes(List<String> datalist,
		Set<String> activeseedsNodes, String node) {
		Set<String> activeNode = new TreeSet<String>();
		if(activeseedsNodes.size()!=0) activeNode.addAll(activeseedsNodes);
		while(node!=null){
			double max = 0.0;
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
		return activeNode;
	}


//	public static Set<String> getActiveNodes(List<String> datalist, String node) {
//		Set<String> activeNode = new TreeSet<String>();
//		while(node!=null){
//			double max = 0.0;
//			String maxNode = null;
//			for (String data : datalist) {
//				if(data.split("\t")[0].equals(node) && !activeNode.contains(node)){
//					if(Double.parseDouble(data.split("\t")[2]) >= max){
//						max = Double.parseDouble(data.split("\t")[2]);
//						maxNode =data ;
//					}
//				}
//			}
//			if(maxNode == null) node = null; 
//			else{
//				activeNode.add(node);
//				node = maxNode.split("\t")[1];
//			}
//		}
//		return activeNode;
//	}
}
