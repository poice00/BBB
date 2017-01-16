package com.mb.maxInfluence;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.SimpleFormatter;
/**
 * 贪心算法与启发式算法的结合
 * 两阶段的影响力最大化算法
 */
@Deprecated
public class Greedy2 {
	public static void main(String[] args) {
		List<String> datalist = Utils.getListDatas("data/follower");//7115节点 103689
		Set<String> nodeSet = Utils.getListNodes("data/follower");
		int k = 50 ; //种子节点k
		Set<String> Nodes = new TreeSet<>();//激活节点的集合
		Set<String> seedsSet = new TreeSet<String>();//种子节点的集合
		seedsSet.add("2268603763");
		seedsSet.add("5544721911");
		seedsSet.add("2012158321");
		seedsSet.add("2002587837");
		seedsSet.add("5379286126");
		seedsSet.add("3229962754");
		seedsSet.add("3166401865");
		seedsSet.add("3365355414");
		seedsSet.add("3241646494");
		seedsSet.add("3174967910");
		seedsSet.add("1849527897");
		seedsSet.add("5392885175");
		seedsSet.add("5446004161");
		seedsSet.add("1947758785");
		seedsSet.add("1717911281");
		seedsSet.add("1295343232");
		seedsSet.add("1147307557");
		seedsSet.add("3550995170");
		seedsSet.add("2531284927");
		seedsSet.add("2697087061");
		seedsSet.add("1211918665");
		seedsSet.add("5833548413");
		seedsSet.add("5344621967");
		seedsSet.add("2890456772");
		seedsSet.add("2898285324");
		for (String seed : seedsSet) {
			Set<String> nodes = getActiveNodes(datalist, seed);//初始激活的集合
			Nodes.addAll(nodes);
		}
		System.out.println("初始激活节点： " + Nodes.size());
		long begin = System.currentTimeMillis();
		for (int i = 0; i < k-seedsSet.size(); i++) {
			long start = System.currentTimeMillis();
			System.out.print("第" + (i+1) +"次节点选择: ");
			String nodeFinal = null;
			int maxSize = 0;
			for (String node : nodeSet) {//遍历每个节点,加入中自己和，计算边际影响值
				int prevSize =  Nodes.size();
				Set<String> activeNodes = getActiveNodes(datalist, Nodes,node);
				int size = activeNodes.size() -  prevSize;
				if(size>maxSize){
					maxSize = size;
					nodeFinal = node ;
					Nodes = activeNodes;
				}
			}
			seedsSet.add(nodeFinal);
			System.out.println("nodeFinal: " + nodeFinal + " 节点集合大小： " + Nodes.size());
			long end = System.currentTimeMillis();
			System.out.println("======节点选择完毕======,耗时：" + (end- start) + "ms");
		}
		long over = System.currentTimeMillis();
//		System.out.println("原始节点大小： " + nodeSet.size());
		System.out.println("最终选取的节点是： " + seedsSet.toString());
		System.out.println("已激活的节点大小： " + Nodes.size());//115, 152, 153, 174, 84 //13, 169, 228, 287, 82
//		System.out.println("已激活的节点： " + Nodes);
		System.out.println("======程序运行完毕======,总耗时：" + (over- begin) + "ms");
		
	}
	

	private static Set<String> getActiveNodes(List<String> datalist,
		Set<String> activeseedsNodes, String node) {
		Set<String> activeNode = new TreeSet<String>();
		activeNode.addAll(activeseedsNodes);
		while(node!=null){
			double max = 0.0;
			String maxNode = null;
			for (String data : datalist) {
				if(data.split("\t")[1].equals(node) && !activeNode.contains(node)){
					if(Double.parseDouble(data.split("\t")[2]) >= max){
						max = Double.parseDouble(data.split("\t")[2]);
						maxNode =data ;
					}
				}
			}
			if(maxNode == null) {
				activeNode.add(node);
				node = null; 
			}else{
				activeNode.add(node);
				node = maxNode.split("\t")[0];
			}
		}
		return activeNode;
	}


	public static Set<String> getActiveNodes(List<String> datalist, String node) {
		Set<String> activeNode = new TreeSet<String>();
		while(node!=null){
			double max = 0.0;
			String maxNode = null;
			for (String data : datalist) {
				if(data.split("\t")[1].equals(node) && !activeNode.contains(node)&&!data.split("\t")[0].equals(node)){
					if(Double.parseDouble(data.split("\t")[2]) >= max){
						max = Double.parseDouble(data.split("\t")[2]);
						maxNode =data ;
					}
				}
			}
			if(maxNode == null) {
				activeNode.add(node);
				node = null; 
			}else{
				activeNode.add(node);
				node = maxNode.split("\t")[0];
			}
		}
		return activeNode;
	}
}
