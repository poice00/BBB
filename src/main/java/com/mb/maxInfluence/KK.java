package com.mb.maxInfluence;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.SimpleFormatter;
/**
 * 2016-10-27 20:20:40
 * System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
 * KK贪心算法
 * @author ssy
 *	独立级联模型
 *	1.网络中的节点分为激活节点和未激活节点，节点只能从未激活节点转化成激活节点。
	2.设定初始集合 ，将 中的所有节点的状态设置为激活状态，同时设定网络中的每个节点间的传播概率 ，通常所有节点间的概率值为固定值，表示节点间传播概率相同。
使用 中的所有节点随机的去激活其所有邻居节点中的未激活节点，直到所有邻居节点中的未激活节点都被尝试激活。
	3.在第 t 步时，利用第 t-1 步生成的激活节点集 ，根据概率 的概率去激活其邻居节点，每个节点对其邻居节点只能进行一次激活，如果激活失败后该节点不能再对
其邻居节点进行激活。如果节点变为激活节点那么将节点加入到 中。
	4.重复上述过程直到没有新的未激活节点变为激活节点，传播过程结束。
 */
public class KK {
	public static void main(String[] args) {
		List<String> datalist = Utils.getListDatas("data/repost.txt");//7115节点 103689
		Set<String> nodeSet = Utils.getListNodes("data/repost.txt");
		System.out.println("节点总数： " + nodeSet.size());
		int k = 50 ; //种子节点k
		Set<String> seedsSet = new TreeSet<String>();//种子节点的集合
		Set<String> Nodes = null;//
		long begin = System.currentTimeMillis();
		for (int i = 0; i < k; i++) {
			long start = System.currentTimeMillis();
			System.out.print("第" + (i+1) +"次节点选择: " + "当前节点集合的大小： " + nodeSet.size());
			String nodeFinal = null;
			int maxSize = 0;
			for (String node : nodeSet) {//遍历未激活的节点,加入激活的节点集合，计算边际影响值  nodeSet在不断减小
				if(seedsSet.size()==0){
					Set<String> activeNodes = getActiveNodes(datalist, node);
					if(activeNodes.size()>maxSize){
						maxSize = activeNodes.size();
						Nodes = activeNodes;
						nodeFinal = node ;
					}
				}else{
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
			nodeSet.removeAll(Nodes);
			System.out.println(" nodeFinal: " + nodeFinal + " 节点集合大小： " + Nodes.size());
//			System.out.println("未激活的节点集合大小： " + Nodes.size());
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
				if(data.split("\t")[1].equals(node) && !activeNode.contains(node)&&!data.split("\t")[0].equals(node) ){
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
