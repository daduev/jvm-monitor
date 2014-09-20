package com.home.jvm.monitor.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;

public class ManagementFactoryUtils {
	private static RuntimeMXBean RUNTIME_MX_BEAN = ManagementFactory.getRuntimeMXBean();
	private static MemoryMXBean MEMORY_MX_BEAN = ManagementFactory.getMemoryMXBean();
	
	public static String getUptime(){
		long uptime = RUNTIME_MX_BEAN.getUptime();
		
		int h = (int) ((uptime / 1000) / 3600);
		int m = (int) (((uptime / 1000) / 60) % 60);
		int s = (int) ((uptime / 1000) % 60);
		
		return h + "h:" + m + "m:" + s + "s";
	}
	
	private static MemoryUsage getMemoryUsage(){
		return MEMORY_MX_BEAN.getHeapMemoryUsage();
	}	
	
	public static Double getUsedMemoryMb(){
		MemoryUsage memoryUsage = getMemoryUsage();
		long usedMemoryByte = memoryUsage.getUsed();
		double usedMemoryKb = usedMemoryByte / 1024;
		double usedMemoryMb = usedMemoryKb / 1024;
		
		return usedMemoryMb;
	}
	
	public static Double getCommitMemoryMb(){
		MemoryUsage memoryUsage = getMemoryUsage();
		long usedMemoryByte = memoryUsage.getCommitted();
		double usedMemoryKb = usedMemoryByte / 1024;
		double usedMemoryMb = usedMemoryKb / 1024;
		
		return usedMemoryMb;		
	}
	
	public static Double getMaxMemoryMb(){
		MemoryUsage memoryUsage = getMemoryUsage();
		long usedMemoryByte = memoryUsage.getMax();
		double usedMemoryKb = usedMemoryByte / 1024;
		double usedMemoryMb = usedMemoryKb / 1024;
		
		return usedMemoryMb;		
	}	
		
}
