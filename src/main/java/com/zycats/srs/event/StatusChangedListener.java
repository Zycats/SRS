package com.zycats.srs.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StatusChangedListener implements ApplicationListener<StatusChangedEvent>{

	@Override
	public void onApplicationEvent(StatusChangedEvent event) {
		
		
	}

	
}
