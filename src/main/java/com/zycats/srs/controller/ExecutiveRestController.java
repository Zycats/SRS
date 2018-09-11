package com.zycats.srs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.service.ITicketService;


@RestController
@RequestMapping("/rest/ticket/*")
public class ExecutiveRestController {

	@Autowired
	private ITicketService ticketService ;
	
	@RequestMapping(value = "get/allTicketsByStatus", method= RequestMethod.POST,
			consumes = "application/json",
			produces="application/json")
	public Iterable<Ticket> getAllTicketByStatus(@RequestBody Map<String, String> data){
		
		System.out.println(data);
		System.out.println(data.get("status"));
		
		return ticketService.getTicketsByStatusAndEngineer(Status.OPEN,
				data.get("employeeId"));
	}
}
