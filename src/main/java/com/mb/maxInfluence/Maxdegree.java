package com.mb.maxInfluence;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.SimpleFormatter;
/**
 * 最大度算法
 */
public class Maxdegree {
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
		seedsSet.add("2268603763");
		seedsSet.add("2012158321");
		seedsSet.add("5544721911");
		seedsSet.add("1849527897");
		seedsSet.add("3229962754");
		seedsSet.add("2531284927");
		seedsSet.add("5446004161");
		seedsSet.add("2002587837");
		seedsSet.add("1787544013");
		seedsSet.add("5392885175");
		seedsSet.add("3174967910");
		seedsSet.add("3550995170");
		seedsSet.add("5379286126");
		seedsSet.add("5239422638");
		seedsSet.add("2697087061");
		seedsSet.add("2643743024");
		seedsSet.add("3108778417");
		seedsSet.add("3690085175");
		seedsSet.add("5566882921");
		seedsSet.add("2714339741");
		seedsSet.add("3757167087");
		seedsSet.add("5833548413");
		seedsSet.add("5344621967");
		seedsSet.add("2898285324");
		seedsSet.add("1975995305");
		seedsSet.add("5252701469");
		seedsSet.add("3365355414");
		seedsSet.add("1909377960");
		seedsSet.add("3166401865");
		seedsSet.add("1677415045");
		seedsSet.add("5663205379");
		seedsSet.add("2104295195");
		seedsSet.add("3568356630");
		seedsSet.add("2421134223");
		seedsSet.add("5541228155");
		seedsSet.add("3960552015");
		seedsSet.add("1947758785");
		seedsSet.add("1147307557");
		seedsSet.add("1644855075");
		seedsSet.add("1211918665");
		seedsSet.add("1717911281");
		seedsSet.add("3241646494");
		seedsSet.add("2460380524");
		seedsSet.add("3817939058");
		seedsSet.add("3887770391");
		seedsSet.add("2128379684");
		seedsSet.add("1681192240");
		seedsSet.add("5249858277");
		seedsSet.add("1295343232");
		seedsSet.add("5577766918");
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
