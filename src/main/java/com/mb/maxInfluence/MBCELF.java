package com.mb.maxInfluence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.catalina.tribes.util.Arrays;

import com.mb.domain.UserrelationId;
import com.mb.preDeal.Util;
import com.mysql.fabric.xmlrpc.base.Array;

/*
 * 最大化算法
 */
public class MBCELF {//444
	public static void main(String[] args) {
		//取得前2k的集合
		String path = "data/MBUser";
		List<String> userList = Util.readperUsers(path);
		int i = 10 ;
		List<String> countlist = preDeal();
		//用着2K节点去激活
		//再剩余的节点中用CELF选取出剩下的10个节点
		List<String> remainednodes =  CELF(userList,countlist);
		//用这20个节点去激活
		int size2 = getActiveNodes(remainednodes,countlist);
		System.out.println(size2);
	}
	private static int getActiveNodes(List<String> inits, List<String> countlist) {
		int size = 0 ;
		for (String init : inits) {
			int a = 0;
			for (String str : countlist) {
				String on = str.split("\t")[0];
				int pro = Integer.parseInt(str.split("\t")[2]);
				if(init.equals(on)&&pro>=10){
//				if(init.equals(on)){
					a++;
				}
			}
			size+=a ;
//			System.out.println("======: " +a);
		}
		return size;
	}
	/**
	 * 
	 * @param userList 所有的节点
	 * @param activeNodes 激活的节点
	 * @param countlist 网络关系
	 * @return
	 */
	private static List<String> CELF(List<String> userList, List<String> countlist) {
		List<String> lists = new ArrayList<>();
		System.out.println(userList.size());
		Map<String, Integer> resultMap =new HashMap<>();
		for (String user : userList) {
			int a = 0;
			for (String str : countlist) {
				String on = str.split("\t")[0];
				int pro = Integer.parseInt(str.split("\t")[2]);
				if(user.equals(on)&&pro>=10){
//				if(user.equals(on)){
					a++;
				}
			}
			if(a==0) continue;
			resultMap.put(user, a);
			System.out.println(user + "\t" +a);
		}
		resultMap = sortMap(resultMap);
		int a = 0 ;
		for (String key : resultMap.keySet()) {
			if(a<10){
				lists.add(key);
				System.out.println(key + " : " + resultMap.get(key));
			}
			a++;
		}
		return lists;
	}
	private static Map<String, Integer> sortMap(Map<String, Integer> resultMap) {
		//实现排序
		Map<String, Integer> resultMap2 =new LinkedHashMap<>();
		List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(resultMap.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for (int i = resultMap.size()-1; i>0; i--) {
        	resultMap2.put(list.get(i).getKey(), list.get(i).getValue());
		}
        return resultMap2;
	}
	private static List<String> preDeal() {
		String allrela = "data/repostAndcomment";
		List<String> lists =  Utils.gettDatas(allrela);
		return lists;
	}
	private static int getActiveNodes(List<String> inits, List<String> countlist,List<String> activeNodes) {
		int size = 0 ;
		for (String init : inits) {
			int a = 0;
			for (String str : countlist) {
				String on = str.split("\t")[0];
				int pro = Integer.parseInt(str.split("\t")[2]);
				if(init.equals(on)&&pro>=10){
//				if(init.equals(on)){
					activeNodes.add(str.split("\t")[1]);
					activeNodes.add(str.split("\t")[0]); 
					a++;
				}
			}
			size+=a ;
//			System.out.println("======: " +a);
		}
		return size;
	}
//	private static List<String> perDeal() {
//		String user = "data/user.txt";//用户
//		String repostAndcomment = "data/repostAndcomment";
//		List<String> lists = new ArrayList<>(); 
//		List<String> userList =Utils.readDataToList(user);
//		List<String> edges = Utils.getEdges(repostAndcomment);//评论
//		Map<String, Double> maps = new HashMap<>();
//		for (String u : userList) {
//			//1.计算节点u在转发和评论中与其他节点交互次数
//			System.out.println("================="+ u +"=================");
//			double inf = getInractionInf(u,edges);
//			if(inf!=0){
//				lists.add(u);
//			}
//		}
//		return lists;
//		
//	}

	private static double getInractionInf(String user, List<String> edges) {
		int index = 0 ;//
		Map<String, Integer> mapr = new HashMap<>();
		for (String redge : edges) {
			if(redge.split("\t")[0].equals(user)){
				index ++;
			}
		}
		return index;
	}
}
