package com.mb.maxInfluence;


public class Node implements Comparable<Node>{
	private boolean isActive;
	private int value ;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public int compareTo(Node o) {
		if(value>o.getValue()) return 1;
		else if(value<o.getValue())  return -1;
		return 0;
	}
}
