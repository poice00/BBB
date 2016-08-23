package com.mb.preDeal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * influ --改进的PRank
 * @author ssy
 *
 */
public class MyPRTets {
	public static void main(String[] args) {
		Map<String, Integer> mapData = Util.getData("C:\\Users\\Administrator\\Desktop\\data.txt");
		String[][] edges =	Util.getEdges(mapData);
		String[] nodes =	Util.getNodes(mapData);
//		GraphMatrix gm = new GraphMatrix(nodes, edges);
//		System.out.println("邻接矩阵构建完毕！");
//		double []X = new double[nodes.length];
//		for (int i = 0; i < X.length; i++) {
//			System.out.println(nodes[i]);
//			X[i] = PreGet.getInflu(nodes[i]);
//		}
//		double[][] MT = Util.readTranspose2From("C:\\Users\\Administrator\\Desktop\\MYPR\\gm.txt",nodes.length,X);
//		Util.writerTo("C:\\Users\\Administrator\\Desktop\\MYPR\\gmT.txt",MT);
		//取得矩阵A
		double[][] A = Util.getAFrom("C:\\Users\\Administrator\\Desktop\\MYPR\\gmT.txt",nodes.length);
		//计算第一次的Influ值
		double []X = new double[nodes.length];
		for (int i = 0; i < X.length; i++) {
			X[i] = 1.0;
		}
		//AX是下一次的PR值
		double[] AX = Util.calcAX(A,X);
		int a = 0 ;
		while(true){
			//0.01  次
			//0.001 次
			//0.0001 次
			//0.00001 9次
			//参考 第四次文献 6.微博中影响力的研究_李翔.caj
//			System.out.println(Arrays.toString(minus(X,AX)));
			if(condition(minus(X,AX),0.00001)){
//				Util.writerTo("C:\\Users\\Administrator\\Desktop\\PR\\myPR.txt", AX);
				List<AA> aaList = new ArrayList<AA>();
				for (int i = 0; i < AX.length; i++) {
					aaList.add(new AA(nodes[i], AX[i]));
				}
				Collections.sort(aaList);
				for (int i = AX.length - 1; i >= AX.length-50; i--) {
					System.out.println(aaList.get(i));
				}
				System.out.println("迭代了: " + a +"次");
				return ;
			}else{
				X = AX ;
				AX = Util.calcAX(A,X);
				System.out.println("=======" + a +"=======");
				a++;
			}
		}
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
