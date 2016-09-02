package com.mb.preDeal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.TreeMap;
/**
 * 距离中心算法
 * @author ssy
 * 距离中心算法的一个基础假设是：社交网络中的节点，如果可以更短的路径到达其他节点，那它应该有更好的机会来影响其他节点。
 * 考察网络中某个节点到其他节点所需的平均路径的长度，也是衡量影响力的一个方法
 */
public class CenterDistA {
	private static int M = 100; //此路不通
	public static void main(String[] args) {
		//sina #西安身边事#
		String path = "data/repost.txt";
		String path2 = "data/comment.txt";
		String path3 = "data/user.txt";
		List<String> userList = readData(path3);
		Map<String, String> maps1 = readData(path,userList);
		Map<String, String> maps2 = readData(path2,userList);
		Map<String, String> maps = merge(maps1,maps2);
		writerTo("result/centerDist_result2",maps);
	}
	public static List<String> readData(String path) {
		List<String> dataList = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				dataList.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}
	public static Map<String, String> readData(String path,List<String> userList) {
		List<String> dataList = new ArrayList<>();
		Map<String, Integer> resultMaps = new LinkedHashMap<>();
		Map<String, String> resultMaps2 = new LinkedHashMap<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				dataList.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String user:userList) {
			int index = 0;
			List<String> nodelist = new ArrayList<>();
			nodelist.add(user);
			List<String> tList = new ArrayList<>();
			int dis = getDist(index,nodelist,dataList,tList);
			if(dis!=0){
				System.out.println(user + "\t" + dis);
				resultMaps.put(user, dis);
//				writerTo("result/maxDegree_result",user + "\t" + 1/((double)dis/nodeCount));
			}
		}
		//这里将map.entrySet()转换成list
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(resultMaps.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        int k =0 ;
        for (int i = resultMaps.size()-1; i>0; i--) {
        	if(k==100) break;
        	double result = (double)list.get(i).getValue()/(userList.size()-1);
        	resultMaps2.put(list.get(i).getKey(), Util.change(result));
        	k++;
		}
        System.out.println("resultMaps2结果空间： " + resultMaps2.size());
		return resultMaps2;
	}
	private static int getDist(int index, List<String> nodelist, List<String> dataList,List<String> tList) {
		int count = 0 ;
		index ++ ;//表示第1~n层
		for (String user : nodelist) {
			tList.add(user);
			List<String> tmplist = new ArrayList<>();
			for (int i = 0; i < dataList.size(); i++) {
				String []s = dataList.get(i).split("	");
				if(user.equals(s[1])&&!tList.contains(s[0])){
					count +=index;
					tmplist.add(s[0]);
				}
			}
			if (tmplist!=null) {
				count += getDist(index,tmplist,dataList,tList);
			}
		}
		return count;
	}
	private static int getNodeCount(List<String> nodelist, List<String> dataList,List<String> tList) {
		int nodecount = 0;
		for (String user : nodelist) {
			tList.add(user);
			List<String> tmplist = new ArrayList<>();
			for (int i = 0; i < dataList.size(); i++) {
				String []s = dataList.get(i).split("	");
				if(user.equals(s[1])&&!tList.contains(s[0])){
					tmplist.add(s[0]);
					nodecount ++;
				}
			}
			if (tmplist!=null) {
				nodecount += getNodeCount(tmplist,dataList,tList);
			}
		}
		return nodecount;
	}
	private static Map<String, String> merge(Map<String, String> maps1, Map<String, String> maps2) {
		Map<String, String> resultMaps = new LinkedHashMap<>();
		Map<String, String> resultMaps2 = new LinkedHashMap<>();
		for (String key : maps1.keySet()) {
			if(maps2.get(key)!=null){
				double value = (0.7*Double.parseDouble(maps1.get(key)) + 0.03*Double.parseDouble(maps2.get(key)));
				resultMaps.put(key, Util.change(value));
			}else 
				resultMaps.put(key, maps1.get(key));
		}
		for (String key : maps2.keySet()) {
			if(resultMaps.get(key)==null){
				resultMaps.put(key, maps2.get(key));
			}
		}
		System.out.println("resultMaps结果空间： " + resultMaps.size());
		//这里将map.entrySet()转换成list
        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(resultMaps.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            public int compare(Entry<String, String> o1,
                    Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        int k =0 ;
        for (int i = resultMaps.size()-1; i>0; i--) {
        	if(k==100) break;
        	resultMaps2.put(list.get(i).getKey(), list.get(i).getValue());
        	k++;
		}
        System.out.println("resultMaps2结果空间： " + resultMaps2.size());
		return resultMaps2;
	}
	private static void writerTo(String path, Map<String, String> maps1) {
		System.out.println("写入中。。。。");
	    File file = new File(path);
	    FileWriter fw = null;
        BufferedWriter writer = null;
        try {
        	 fw = new FileWriter(file);
             writer = new BufferedWriter(fw);
             for (String key : maps1.keySet()) {
             	writer.write(key + "\t" + maps1.get(key));
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

	private static  void print(Map<String, Integer> maps) {
		for (String key : maps.keySet()) {
			System.out.println(key + "\t" + maps.get(key));
		}
	}

	public static String change(double dd) {
		DecimalFormat  df=new DecimalFormat("#.###############");//保留15位小数
		return df.format(dd);
	}
}
