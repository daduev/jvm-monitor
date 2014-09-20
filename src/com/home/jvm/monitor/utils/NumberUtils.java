package com.home.jvm.monitor.utils;

import java.math.BigDecimal;

public class NumberUtils {
	public static double roundDefault(Double value){
		return round(value, 2);
	}
	
	public static double round(Double value, int scale){
		BigDecimal bd = BigDecimal.valueOf(value);
		return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static int roundInteger(double d){
		long l = Math.round(d);
		int i = (int)(l - (l % 100));
		return i;
	}
}
