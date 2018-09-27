package com.zycats.srs.scheduler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.exception.InsufficientPriviledgesException;
import com.zycats.srs.service.ITicketService;

@Component
public class PartScheduler
{ 
	@Autowired
	private ITicketService ticketService;
	
	@Scheduled(fixedDelay = 3000)
	public void scheduleTaskWithFixedDelay() throws InsufficientPriviledgesException
	{
		System.out.println("scheduler called");
		Iterable<Ticket> pendingTickets = ticketService.findAllTicketsByStatus(Status.PENDING_APPROVAL);
		
		for (Ticket ticket : pendingTickets)
		{
			long raiseTime = ticket.getDatetime().getTime();
			long currentTime = new Date().getTime();
			int days = getDaysFromMills(currentTime - raiseTime);
			
			if (days >= 3)
			{
				ticket.setStatus(Status.CLOSED);
				ticketService.update(ticket);
				System.out.println("ticket " + ticket.getId() + " closed");
			}
		}
	}
	
	private int getDaysFromMills(long mills)
	{
		return (int) (mills / (1000*60*60*24));
	}
}
