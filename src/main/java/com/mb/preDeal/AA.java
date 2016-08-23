package com.mb.preDeal;

public class AA implements Comparable<AA>{
	 String username;
	 double influ;
	 public AA(String username, double influ) {
		super();
		this.username = username;
		this.influ = influ;
	 }
	@Override
	public int compareTo(AA o) {
		if(influ>o.influ) return 1;
		else if (influ<o.influ) return -1;
		return 0;
	}
	@Override
	public String toString() {
		return username + ": " + influ;
	}
	 
}
