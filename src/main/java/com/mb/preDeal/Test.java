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
import java.util.Map.Entry;
import java.util.TreeMap;

public class Test {
	public static void main(String[] args) {
//		double dd = 0.15 / 9830 ; //E表示10为底的指数
//		System.out.println("slope:"+dd);
//		System.out.println(Double.parseDouble(change(dd)));
//		double []a = {1.2,3.4,5.6};
//		double []b = a;
//		System.out.println(0.0001>7.381262434808531E-4);
//		BigInteger b1 = new BigInteger("10000000000");
//		long a = 1024*1024;
//		System.out.println(a);
//		BigInteger b2 = new BigInteger(String.valueOf(a));
//		
//		System.out.println(b1.divide(b2).divide(new BigInteger("1024")) + "G");
//		int a[][] = new int[46000][46000];
//		Runtime run = Runtime.getRuntime();
//		long total = run.totalMemory();
//		long free = run.freeMemory();
//		System.out.println("total= " + total/1024/1024/1024 + " G");
//		System.out.println("free = " + free/1024/1024/1024 + " G");
//		System.out.println("ok");
//		1.0748599521548385E-7
//		1.0409455342141882E-7
//		1.0034776209977281E-7
//		System.out.println(change(1.0748599521548385E-7));
		//sina #西安身边事# sinaPageRnak
		/*String path = "result/repost/result0.00001";
		String path2 = "result/comment/result0.00001";
		Map<String, String> maps1 = readData(path);
//		print(maps1);
		Map<String, String> maps2 = readData(path2);
		Map<String, String> maps = merge(maps1,maps2);
//		print(maps);
		writerTo("result/result0.00001",maps);*/
		//sina #西安身边事# 原始的PageRank
		String path = "compare/repost/ori_result0.00001";
		String path2 = "compare/comment/ori_result0.00001";
		Map<String, String> maps1 = readData(path);
//		print(maps1);
		Map<String, String> maps2 = readData(path2);
		Map<String, String> maps = merge(maps1,maps2);
//		print(maps);
		writerTo("result/ori_result0.00001",maps);
	}

	private static void writerTo(String path, Map<String, String> maps) {
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

	private static void print(Map<String, String> maps) {
		for (String key : maps.keySet()) {
			System.out.println(key + "\t" + maps.get(key));
		}
	}

	private static Map<String, String> merge(Map<String, String> maps1, Map<String, String> maps2) {
		Map<String, String> resultMaps = new LinkedHashMap<>();
		Map<String, String> resultMaps2 = new LinkedHashMap<>();
		for (String key : maps1.keySet()) {
			if(maps2.get(key)!=null){
				double value = 0.7*Double.parseDouble(maps1.get(key)) + 0.3*Double.parseDouble(maps2.get(key));
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

	public static String change(double dd) {
		DecimalFormat  df=new DecimalFormat("#.###############");//保留15位小数
		return df.format(dd);
	}
	public static Map<String, String> readData(String path) {
		Map<String, String> maps = new LinkedHashMap<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			int i = 0 ;
			while((str=br.readLine())!=null){
				if(i==100) break;
				String []s = str.split("	");
				maps.put(s[0], Util.change(Double.parseDouble(s[1])));
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("读取成功： " + maps.size());
		return maps;
	}
}
