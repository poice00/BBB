package com.mb.preDeal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
/**
 * 为了方便 直接copy repost
 * @author ssy
 *
 */
public class GraphMatrix2 {
	private String[] mVexs;       // 顶点集合
	private int[][] mMatrix;    // 邻接矩阵
    /**
     * 创建图(用已提供的矩阵)
     * @param nodes	顶点数组
     * @param edges	边数组
     */
    public GraphMatrix2(String[] nodes, String[][] edges) {
    	System.out.println("邻接矩阵构建中。。。");
        // 初始化"顶点数"和"边数"
        int vlen = nodes.length;
        int elen = edges.length;

        // 初始化"顶点"
        mVexs = new String[vlen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = nodes[i];

        // 初始化"边"
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < elen; i++) {
            // 读取边的起始顶点和结束顶点
            int p1 = getPosition(edges[i][0]);
            int p2 = getPosition(edges[i][1]);
            if(mMatrix[p1][p2] != 0)
            	mMatrix[p1][p2] += 1;
            else
            	mMatrix[p1][p2] = 1;
        }
		writerTo("E:\\SinaPR\\gm.txt", mMatrix);
		System.out.println("邻接矩阵构建完毕！");
    }
	private void writerTo(String path, int[][] m) {
		System.out.println("写入中。。。。");
	    File file = new File(path);
	    FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m.length; j++)
              	    writer.write(m[i][j]+"	");
                writer.newLine();
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("写入完毕。。。。");
	 }
		
	public int[][] getmMatrix() {
		return mMatrix;
	}
	public void setmMatrix(int[][] mMatrix) {
		this.mMatrix = mMatrix;
	}
	/*
     * 返回s位置
     */
    public int getPosition(String s) {
        for(int i=0; i<mVexs.length; i++)
            if(mVexs[i].equals(s))
                return i;
        return -1;
    }
    /*
     * 判断是否有邻居节点
     */
    public boolean hasNext(int v){
    	if (v<0 || v>(mVexs.length-1))
            return false;

        for (int i = 0; i < mVexs.length; i++)
            if (mMatrix[v][i] != 0)
                return true;

        return false;
    }
    
    /*
     * 返回顶点v的第一个邻接顶点的索引，失败则返回-1
     */
    public int firstVertex(int v) {

        if (v<0 || v>(mVexs.length-1))
            return -1;

        for (int i = 0; i < mVexs.length; i++)
            if (mMatrix[v][i] != 0)
                return i;

        return -1;
    }

    /*
     * 返回顶点v相对于w的下一个邻接顶点的索引，失败则返回-1
     */
    public int nextVertex(int v, int w) {

        if (v<0 || v>(mVexs.length-1) || w<0 || w>(mVexs.length-1))
            return -1;

        for (int i = w + 1; i < mVexs.length; i++)
            if (mMatrix[v][i] != 0)
                return i;

        return -1;
    }
    public void BFS(){
	    boolean[] visited = new boolean[mVexs.length];  // 顶点访问标记
	    //用队列存放所有依次要访问元素  
	    Queue<String> q=new LinkedList<>();  
	    for (int i = 0; i < mVexs.length; i++) visited[i] = false;
	    System.out.printf("BFS: ");
	    q.offer(mVexs[0]);
	    visited[getPosition(mVexs[0])]=true;
	    while(!q.isEmpty()){
	    	String c = q.poll();
	 	   	System.out.print(c+"\t");
	 	   	int j = getPosition(c);
	 	   	for (int k = firstVertex(j); k >= 0; k = nextVertex(j, k)) {
	     		if(visited[k]==false){
	     			q.offer(mVexs[k]);
	     			visited[getPosition(mVexs[k])]=true;
				}
	     	}
	     }
	     System.out.printf("\n");
    }
    public String[] getmVexs() {
		return mVexs;
	}
	public void setmVexs(String[] mVexs) {
		this.mVexs = mVexs;
	}
    
    /*
     * 打印矩阵队列图
     */
    public void print() {
        System.out.printf("邻接矩阵的表示:\n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.print(mMatrix[i][j]+"\t");
            System.out.printf("\n");
        }
    }
	public void writerTo(String path, double[][] m) {
		System.out.println("写入中。。。。");
	    File file = new File(path);
	    FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m.length; j++)
              	    writer.write(m[i][j]+"	");
                writer.newLine();
            }
//            for (int i = 0; i < mVexs.length; i++) {
//            	for (int j = 0; j < mVexs.length; j++)
//            		writer.write(mMatrix[i][j]+"	");
//            	writer.newLine();
//            }
   		
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("写入完毕。。。。");
	 }
}
