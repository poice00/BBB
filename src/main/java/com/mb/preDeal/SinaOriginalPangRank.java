package com.mb.preDeal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
/**
 * 新浪微博 转发和评论网络未改进的PR
 * @author ssy
 * PageRank：
 * 被越多优质的网页所指向的，它是优质网页的概率就越大。
 * 被越多影响力大的节点所指向，它的影响力大的概率就越大。
 */
public class SinaOriginalPangRank {
	public static void main(String[] args) {
		String []nodes = Util.getTMNodes("data/user.txt");
		//1.
//		String[][] edges =	Util.getTMEdges("data/repost.txt");
//		GraphMatrix gm = new GraphMatrix(nodes, edges);
		//得到转
		//2.
//		float[][] MT = Util.readTranspose2From("E:\\SinaPR\\comment\\gm.txt",nodes.length);
//		Util.writerTo("E:\\SinaPR\\comment\\gmT.txt",MT);
//		float[][] MT = Util.readTranspose2From("E:\\SinaPR\\ori_gm.txt",nodes.length);
//		Util.writerTo("E:\\SinaPR\\ori_gmT.txt",MT);
		//取得矩阵A
		//3.
		float[][] A = Util.getAFrom("E:\\SinaPR\\ori_gmT.txt",nodes.length);
		//计算第一次的PR值
		float []X = new float[nodes.length];
		for (int i = 0; i < X.length; i++) {
			X[i] = 1.0f;
		}
		//AX是下一次的PR值
		float[] AX = Util.calcAX(A,X);
		int a = 0 ;
		long start = System.currentTimeMillis();
		System.out.println("开始迭代。。。");
		double threshold = 0.00001;
		while(true){
			//0.01 
			//0.001 
			//0.0001 
			//0.00001
			if(condition(minus(X,AX),threshold)){
				System.out.println("迭代了: " + a +"次");
				System.out.println("耗时： " + (System.currentTimeMillis()-start));
				List<AA> aaList = new ArrayList<AA>();
				for (int i = 0; i < AX.length; i++) {
					aaList.add(new AA(nodes[i], AX[i]));
				}
				Collections.sort(aaList);
				int k = 100;//取前K个
				Util.writerTo("E:\\SinaPR\\ori_result" + threshold, aaList,k);
//				for (int i = AX.length - 1; i >= AX.length-k; i--) {
//					System.out.println(aaList.get(i));
//				}
				return ;
			}else{
				X = AX ;
				AX = Util.calcAX(A,X);
				System.out.println("==============");
				a++;
			}
		}
    }
	private static float[] minus(float[] aX, float[] x) {
		for (int i = 0; i < x.length; i++) {
			aX[i] = Math.abs(aX[i] -x[i]);
		}
		return aX;
	}
	private static boolean condition(float[] o, double condition) {
		boolean flag[] =new boolean[o.length];
		for (int i = 0; i < o.length; i++) {
			if(o[i]<condition) flag[i] = true ;
			else flag[i] = false;
		}
		for (int i = 0; i < flag.length; i++) {
			if(flag[i] == false){
				return false;
			}
		}
		return true;
	}
	
}
