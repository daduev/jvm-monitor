package com.home.jvm.monitor.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
	private static Calendar CALENDAR = Calendar.getInstance();
	
	public static int currentHour(Date time){
		CALENDAR.setTime(time);
		return CALENDAR.get(Calendar.HOUR);
	}
	
	public static int currentMinute(Date time){
		CALENDAR.setTime(time);
		return CALENDAR.get(Calendar.MINUTE);		
	}
}
