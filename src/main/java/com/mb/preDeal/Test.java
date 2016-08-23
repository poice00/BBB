package com.mb.preDeal;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
//		double dd = 0.15 / 9830 ; //E表示10为底的指数
//		System.out.println("slope:"+dd);
//		System.out.println(Double.parseDouble(change(dd)));
//		double []a = {1.2,3.4,5.6};
//		double []b = a;
//		System.out.println(0.0001>7.381262434808531E-4);
		BigInteger b1 = new BigInteger("10000000000");
		long a = 1024*1024;
		System.out.println(a);
		BigInteger b2 = new BigInteger(String.valueOf(a));
		
		System.out.println(b1.divide(b2).divide(new BigInteger("1024")) + "G");
	}

	public static String change(double dd) {
		DecimalFormat  df=new DecimalFormat("#.######");//保留六位小数
		return df.format(dd);
	}
}
