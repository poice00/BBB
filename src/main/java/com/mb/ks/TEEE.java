package com.mb.ks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TEEE {
	public static void main(String[] args) {
		String data = "data/att";
		List<String> userList = Utils.readDataToList(data);
		Map<Integer, Integer> maps = new HashMap<>();
		for (String str : userList) {
			int a = Integer.parseInt(str);
			if(maps.get(a)==null) maps.put(a, 1);
			else maps.put(a, maps.get(a)+1);
		}
		for (Integer i : maps.keySet()) {
			if(i!=0)
//				System.out.println(i + ",");
				System.out.println(maps.get(i) + ",");
		}
	}
}
