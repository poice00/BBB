package com.mb.ks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		List<String> edges = new ArrayList<>();
		Set<String> minNodes = new HashSet<>();
		edges.add("A	B");
		edges.add("A	D");
		edges.add("A	E");
		edges.add("B	D");
		edges.add("B	E");
		edges.add("D	E");
		minNodes.add("D");
		minNodes.add("A");
		minNodes.add("B");
		for (int i = 0;i<edges.size();i++) {//移除边
			String from = edges.get(i).split("\t")[0];
			String to = edges.get(i).split("\t")[1];
			for (String node : minNodes) {//移除节点
				if(from.equals(node)||to.equals(node)) {
					System.out.println(node+" -- "+edges.get(i));
					edges.remove(i);
					i--;
					break;
				}
			}
		}
		System.out.println(edges.toString());
		float d = 4.7f;
		System.out.println(d-0.3f);
	}
}
