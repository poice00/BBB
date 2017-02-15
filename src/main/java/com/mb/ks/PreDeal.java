package com.mb.ks;

import java.util.ArrayList;
import java.util.List;

public class PreDeal {
	public static void main(String[] args) {
		List<String> data = Utils.readDataToList("weibo/weibo.txt");
		System.out.println(data.size());
		String str = "5251583324";//5158748474
		String str2 = "5577766918";//5158748474
//		List<String> result = getRes(data,str);
		List<String> result2 = getRes(data,str2);
//		Utils.writerresultTo("weibo/weibo1", result);
		Utils.writerresultTo("weibo/weibo2", result2);
		System.out.println(result2.size());
	}

	private static List<String> getRes(List<String> data, String str) {
		List<String> res = new ArrayList<>();
		for (String s : data) {
			if(s.contains(str))
				res.add(s);
		}
		return res;
	}
}
