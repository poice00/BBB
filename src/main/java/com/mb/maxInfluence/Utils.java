package com.mb.maxInfluence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Utils {
	
	/**
	 * 独立级联模型的传播概率（随机的初始化）
	 * @return 传播概率Puv
	 */
	public static double getInitPro() {
		//在0.1-1之间随机取个数字，作为独立级联模型的传播概率
		Random ran = new Random();
		double d = ran.nextDouble()*0.9 + 0.1;
		BigDecimal b = new BigDecimal(d);  
		double ret = b.setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
		return ret;
	}
	/**
	 * 获取节点的集合
	 * @param datas 数据的矩阵
	 * @param col 列
	 * @param row 行
	 * @return 节点的集合
	 */
	public static Node[] getNodes(int[][] datas, int col, int row) {
		Set<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++){
            	set.add(datas[i][j]);
            }
        }
		Node[] nodes = new Node[set.size()];
		int i =0 ;
		for (Integer integer : set) {
			Node node =new Node();
			node.setValue(integer);
			node.setActive(false);
			nodes[i] = node;
			i++;
		}
		return nodes;
	}
	/**
	 * 读取数据,边的二位数组
	 * @param path 数据的路径
	 * @return  二维数组 
	 * 			一行表示某人投给了默认一票
	 */
	public static int[][] getData(String path){
//		int[][] x = new int[43][2];
		int[][] x = new int[103689][2];
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
				if(!str.startsWith("#")){
					String []s = str.split("\t");
					int[]ss = new int[2];
					ss[0] = Integer.parseInt(s[0]);
					ss[1] = Integer.parseInt(s[1]);
					for (int j = 0; j < 2; j++) {
						x[i][j] = ss[j];
					}
					i++;
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return x;
	}
	public static List<String> getListDatas(String path) {
		List<String> lists = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
				double pro = getInitPro();
				lists.add(str + "\t" + pro);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lists;
	}
	public static Set<String> getListNodes(String path) {
		Set<String> sets = new TreeSet<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
				sets.add(str.split("\t")[0]);
				sets.add(str.split("\t")[1]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sets;
	}
}
