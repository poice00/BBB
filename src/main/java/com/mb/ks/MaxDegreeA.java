package com.mb.ks;

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
import java.util.Map.Entry;
import java.util.TreeMap;
/**
 * 最大度算法 
 * @author ssy
 * 最大度算法的一个基本假设是：越是拥有更多和其他节点链接的节点，越是可能让更多人看到自己发布的信息。
 * 因此，一个朴素的想法是，社交网络中度数越大的节点，其影响力也就越大
 *
 */
public class MaxDegreeA {
	public static void main(String[] args) {
		//sina #西安身边事#
		String path = "data/repost.txt";
		String path2 = "data/comment.txt";
		String follower = "data/follower";//关注
//		Map<String, Integer> maps1 = readData(path);
//		print(maps1);
//		Map<String, Integer> maps2 = readData(path2);
//		print(maps2);
//		Map<String, Integer> maps = merge(maps1,maps2);
//		print(maps);
		Map<String, Integer> maps = readData(follower);
		writerTo("result/KS_RELATED/maxDegree",maps);
	}
	private static Map<String, Integer> merge(Map<String, Integer> maps1, Map<String, Integer> maps2) {
		Map<String, Integer> resultMaps = new LinkedHashMap<>();
		Map<String, Integer> resultMaps2 = new LinkedHashMap<>();
		for (String key : maps1.keySet()) {
			if(maps2.get(key)!=null){
				int value = (int) (0.7*maps1.get(key) + 0.3*maps2.get(key));
				resultMaps.put(key, value);
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
        	resultMaps2.put(list.get(i).getKey(), list.get(i).getValue());
        	k++;
		}
        System.out.println("resultMaps2结果空间： " + resultMaps2.size());
		return resultMaps2;
	}
	private static void writerTo(String path, Map<String, Integer> maps) {
		System.out.println("写入中。。。。");
	    File file = new File(path);
	    FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (String key : maps.keySet()) {
            	writer.write(key + "\t" + maps.get(key));
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
	public static Map<String, Integer> readData(String path) {
		Map<String, Integer> resultMaps = new LinkedHashMap<>();
		Map<String, Integer> resultMaps2 = new LinkedHashMap<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				String []s = str.split("	");
				if(null == resultMaps.get(s[1]))
					resultMaps.put(s[1], 1);
				else 
					resultMaps.put(s[1], resultMaps.get(s[1])+1);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
        	resultMaps2.put(list.get(i).getKey(), list.get(i).getValue());
        	k++;
		}
        System.out.println("resultMaps2结果空间： " + resultMaps2.size());
		return resultMaps2;
	}
}
