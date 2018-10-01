package com.zycats.srs.event;

import org.springframework.context.ApplicationEvent;

import com.zycats.srs.entity.Report;

public class StatusChangedEvent extends ApplicationEvent{

	private Report report;
	
	public StatusChangedEvent(Object source,Report report) {
		super(source);
		this.report = report;
		
	}
	
	public Report getReport(){
		return report;
	}
}
