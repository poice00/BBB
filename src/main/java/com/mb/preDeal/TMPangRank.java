package com.mb.preDeal;

import java.util.List;
/**
 * 腾讯微博 原始的PR
 * @author ssy
 *
 */
public class TMPangRank {
	public static void main(String[] args) {
//		String[] nodes =	Util.getTMNodes("C:\\Users\\Administrator\\Desktop\\微博影响力分析\\result_2user");
//		String[][] edges =	Util.getTMEdges("C:\\Users\\Administrator\\Desktop\\微博影响力分析\\result_2sns");
//		GraphMatrix gm = new GraphMatrix(nodes, edges);
//		System.out.println("邻接矩阵构建完毕！");
		//得到转置
//		double[][] MT = Util.readTranspose2From("C:\\Users\\Administrator\\Desktop\\PR\\gm.txt",nodes.length);
//		Util.writerTo("C:\\Users\\Administrator\\Desktop\\PR\\gmT.txt",MT);
//		//取得矩阵A
//		double[][] A = Util.getAFrom("C:\\Users\\Administrator\\Desktop\\PR\\gmT.txt",nodes.length);
//		//计算第一次的PR值
//		double []X = new double[nodes.length];
//		for (int i = 0; i < X.length; i++) {
//			X[i] = 1.0;
//		}
//		//AX是下一次的PR值
//		double[] AX = Util.calcAX(A,X);
//		int a = 0 ;
//		long start = System.currentTimeMillis();
//		while(true){
//			//0.01 62次
//			//0.001 80次
//			//0.0001 98次
//			//0.00001 116次
//			//参考 第四次文献 6.微博中影响力的研究_李翔.caj
//			if(condition(minus(X,AX),0.001)){
//				System.out.println("迭代了: " + a +"次");
//				System.out.println("耗时： " + (System.currentTimeMillis()-start));
//				List<AA> aaList = new ArrayList<AA>();
//				for (int i = 0; i < AX.length; i++) {
//					aaList.add(new AA(nodes[i], AX[i]));
//				}
//				Collections.sort(aaList);
//				Util.writerTo("C:\\Users\\Administrator\\Desktop\\PR\\result", aaList);
//				for (int i = AX.length - 1; i >= AX.length-50; i--) {
//					System.out.println(aaList.get(i));
//				}
//				return ;
//			}else{
//				X = AX ;
//				AX = Util.calcAX(A,X);
//				System.out.println("==============");
//				a++;
//			}
//		}
		List<String> resultList = Util.getFromSNS("C:\\Users\\Administrator\\Desktop\\PR\\result","C:\\Users\\Administrator\\Desktop\\微博影响力分析\\result_final2");
		Util.writerresultTo("C:\\Users\\Administrator\\Desktop\\PR\\result2", resultList);
		
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
