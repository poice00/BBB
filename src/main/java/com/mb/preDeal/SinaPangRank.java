package com.mb.preDeal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
/**
 * 腾讯微博 原始的PR
 * @author ssy
 *
 */
public class SinaPangRank {
	public static void main(String[] args) {
		//1.
		String []nodes = Util.getTMNodes("data/usert.txt");
		String[][] edges =	Util.getTMEdges("data/repostt.txt");
		GraphMatrix2 gm2 = new GraphMatrix2(nodes, edges);
		//得到转
		//2.
//		double[][] MT = Util.readTranspose2From("C:\\Users\\Administrator\\Desktop\\PR\\gm.txt",nodes.length);
//		Util.writerTo("C:\\Users\\Administrator\\Desktop\\PR\\gmT.txt",MT);
		//取得矩阵A
		//3.
		double[][] A = Util.getAFrom("C:\\Users\\Administrator\\Desktop\\PR\\gmT.txt",nodes.length);
		//计算第一次的PR值
		double []X = new double[nodes.length];
		for (int i = 0; i < X.length; i++) {
			X[i] = 1.0;
		}
		//AX是下一次的PR值
		double[] AX = Util.calcAX(A,X);
		int a = 0 ;
		long start = System.currentTimeMillis();
		while(true){
			//0.01 62次
			//0.001 80次
			//0.0001 98次
			//0.00001 116次
			//参考 第四次文献 6.微博中影响力的研究_李翔.caj
			if(condition(minus(X,AX),0.001)){
				System.out.println("迭代了: " + a +"次");
				System.out.println("耗时： " + (System.currentTimeMillis()-start));
				List<AA> aaList = new ArrayList<AA>();
				for (int i = 0; i < AX.length; i++) {
					aaList.add(new AA(nodes[i], AX[i]));
				}
				Collections.sort(aaList);
				int k = 3;//取前K个
				Util.writerTo("C:\\Users\\Administrator\\Desktop\\PR\\result", aaList,k);
				for (int i = AX.length - 1; i >= AX.length-k; i--) {
					System.out.println(aaList.get(i));
				}
				return ;
			}else{
				X = AX ;
				AX = Util.calcAX(A,X);
				System.out.println("==============");
				a++;
			}
		}
//		
    }
	private static double[] minus(double[] aX, double[] x) {
		for (int i = 0; i < x.length; i++) {
			aX[i] = Math.abs(aX[i] -x[i]);
		}
		return aX;
	}
	private static boolean condition(double[] o, double condition) {
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
