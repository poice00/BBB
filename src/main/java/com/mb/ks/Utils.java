package com.mb.ks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
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
	public static Set<String> getNodes(String path) {
		Set<String> sets = new HashSet<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				sets.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("节点: " + sets.size());
		return sets;
	}
	public static List<String> getEdges(String path) {
		List<String> lists = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String str= null;
			while((str=br.readLine())!=null){
				lists.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("边: " + lists.size());
		return lists;
	}
}