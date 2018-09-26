package com.zycats.srs.scheduler;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zycats.srs.service.ITicketService;

@Component
public class PartScheduler
{
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); 
	
	@Autowired
	private ITicketService ticketService;
	
	@Scheduled(fixedDelay = 3000)
	public void scheduleTaskWithFixedDelay()
	{
		
	}
}
