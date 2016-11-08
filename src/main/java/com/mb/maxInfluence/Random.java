package com.mb.maxInfluence;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.SimpleFormatter;
/**
 * Random
 */
public class Random {
	public static void main(String[] args) {
		int k  = 100;
		int sum = 0 ;
		for (int i = 0; i < k; i++) {//蒙特卡洛模拟
			System.out.println("激活节点： " + maxDegree());
			sum += maxDegree();
		}
		System.out.println("over节点： " + sum/100);
		
	}


	private static int maxDegree() {
		List<String> datalist = Utils.getListDatas("data/repost.txt");//7115节点 103689
		Set<String> nodeSet = Utils.getListNodes("data/repost.txt");
		int k = 50 ; //种子节点k
		Set<String> Nodes = new TreeSet<>();//激活节点的集合
		Set<String> seedsSet = new TreeSet<String>();//种子节点的集合
		String []tmp = new String[nodeSet.size()];
		int x = 0;
		for (String s : nodeSet) {
			tmp[x] = s ;
			x++;
		}
		for (int i = 0; i < k; i++) {
			String node = tmp[(int) (Math.random()*(nodeSet.size()))];
			seedsSet.add(node);
		}
		for (String seed : seedsSet) {
			Set<String> nodes = getActiveNodes(datalist, seed);//初始激活的集合
			Nodes.addAll(nodes);
		}
		return Nodes.size();
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
