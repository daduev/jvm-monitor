package com.home.jvm.monitor.pages;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.home.jvm.monitor.utils.DateTimeUtils;
import com.home.jvm.monitor.utils.FContext;
import com.home.jvm.monitor.utils.ManagementFactoryUtils;
import com.home.jvm.monitor.utils.NumberUtils;


@SessionScoped
@Named
public class IndexBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	@FContext
	private FacesContext context;	
	
	private LineChartModel lineCharModel = new LineChartModel();
	private LineChartSeries charSeries = new LineChartSeries();
	
	private int nextHour;
	private int startMinute;
	
	public IndexBean() {}
	
	@PostConstruct
	public void init(){
		initMemoryChart();
	}
	
	@PreDestroy
	public void shutdown(){
	}	
	
	public void pollListener(){}
	
	public void performGC(ActionEvent event){
		System.gc();
	}
	
	private void initMemoryChart(){
		Date curTime = new Date();
		
		nextHour = DateTimeUtils.currentHour(curTime) + 1;
		startMinute = DateTimeUtils.currentMinute(curTime);
		
		charSeries.setShowMarker(false);
		
		lineCharModel.addSeries(charSeries);
		
		Axis axisX = new DateAxis("Time, hh:ss");
		axisX.setTickFormat("%H:%M");
		axisX.setMin(curTime.getTime());
		axisX.setTickInterval("300");
		lineCharModel.getAxes().put(AxisType.X, axisX);
		
		Axis axisY = lineCharModel.getAxis(AxisType.Y); 
		axisY.setLabel("Memory, mb");
		axisY.setMin(0);
		axisY.setMax(NumberUtils.roundInteger(ManagementFactoryUtils.getUsedMemoryMb()) + 200);
		axisY.setTickInterval("100");
		
		lineCharModel.setShowDatatip(false);
	}

	private LineChartModel updateMemoryChart(){
		Date curTime = new Date();
		
		Axis axisY = lineCharModel.getAxis(AxisType.Y);
		int maxBound = (int) axisY.getMax();
		int curMemory = NumberUtils.roundInteger(ManagementFactoryUtils.getUsedMemoryMb());
		
		if(curMemory >= maxBound){
			axisY.setMax(curMemory + 100);
		}
		
		if(DateTimeUtils.currentHour(curTime) == nextHour && DateTimeUtils.currentMinute(curTime) == startMinute){
			Axis axisX = lineCharModel.getAxis(AxisType.X);
			int tick = Integer.parseInt(axisX.getTickInterval());
			int nextTick = tick + 300;
			axisX.setTickInterval(String.valueOf(nextTick));
			nextHour++;
		}
		
		lineCharModel.getAxis(AxisType.X).setMax(curTime.getTime());
		charSeries.getData().put(curTime.getTime(), ManagementFactoryUtils.getUsedMemoryMb());
	
		return lineCharModel;
	}

	public LineChartModel getLineCharModel() {
		return lineCharModel = updateMemoryChart();
	}
	

}
