package com.mb.maxInfluence;

import java.util.ArrayList;
import java.util.List;

public class MaxCalc {
	public static void main(String[] args) {
		List<String> countlist = preDeal();
		for(int i = 2;i<=20;i +=2){
			String degee = "max/degree";
			String cent = "max/cent";
			String ks = "max/ks";
			String mdd = "max/mdd";
			String mib = "max/mib";
			String MB = "max/MB";
			String smc = "result/KS_RELATED/SMC";
			List<String> inits = Utils.readInitial(smc,i);
			int size = getActiveNodes(inits,countlist);
			System.out.println(i + " : " + size);
		}
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

	private static List<String> preDeal() {
		String allrela = "data/repostAndcomment";
		List<String> lists =  Utils.gettDatas(allrela);
		return lists;
		
//		List<String> resultList = new ArrayList<>();
//		for (String str : lists) {
////			System.out.println(str);
//			String on = str.split("\t")[0];
//			String tw = str.split("\t")[1];
//			int th = Integer.parseInt(str.split("\t")[2]);
//			//统计此节点的交互总次数
//			int count = getCount(on,lists);
//			System.out.println(count+"..");
//			double pro = (double)th / count ;
//			resultList.add(on+"\t"+tw+"\t"+pro);
//		}
//		for (String s : resultList) {
//			System.out.println(s);
//		}
	}

	private static int getCount(String on, List<String> lists) {
		int count = 0;
		for (String str : lists) {
			if(on.equals(str.split("\t")[0])){
				String th = str.split("\t")[2];
				count += Integer.parseInt(th);
			}
		}
		return count;
	}
}
