package com.zycats.srs.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.zycats.srs.entity.Report;

public class StatusChangedPublish {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	public void publishEvent(Report report){
		
		System.out.println("status changed");
		StatusChangedEvent statusChangedEvent = new StatusChangedEvent(this, report);
		
		applicationEventPublisher.publishEvent(statusChangedEvent);
	}
	
}
