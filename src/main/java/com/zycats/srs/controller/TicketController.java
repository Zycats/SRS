package com.zycats.srs.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.service.ITicketService;

@RestController
@RequestMapping("/rest/ticket/*")
public class TicketController {

	@Autowired
	private ITicketService ticketService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Ticket addTicket(@RequestBody Ticket ticket, Authentication auth, HttpServletRequest request) {
		return ticketService.add(ticket, auth, request.getRemoteAddr());
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public boolean deleteTicket(@PathVariable int id) {
		return ticketService.delete(id);
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.POST)
	public Ticket getTicketById(@PathVariable int id) {
		return ticketService.getById(id);
	}

	@RequestMapping(value = "get/all", method = RequestMethod.GET)
	public Iterable<Ticket> getAllTicket() {
		return ticketService.getAll();
	}
	
	// ticket related rest controls
	
	//accepts status and employeeId and returns all the tickets
		@RequestMapping(value = "get/status/empId", method= RequestMethod.POST,
				consumes = "application/json",
				produces="application/json")
		public Iterable<Ticket> getAllTicketByStatus(@RequestBody Map<String, String> data){
			
			return ticketService.getTicketsByStatusAndEngineer(Status.valueOf(data.get("status")),
					data.get("employeeId"));
		}
	
	//accepts category and employeeId and returns all the tickets
			@RequestMapping(value = "get/category/empId", method= RequestMethod.POST,
					consumes = "application/json",
					produces="application/json")
			public Iterable<Ticket> getAllTicketByCategory(@RequestBody Map<String, String> data){
				
				return ticketService.findAllTicketsByCategory(Integer.parseInt(data.get("category_id")),
						data.get("employeeId"));
			}
				
	//accepts sub_category and employeeId and returns all the tickets
			@RequestMapping(value = "get/sub_category/empId", method= RequestMethod.POST,
					consumes = "application/json",
					produces="application/json")
			public Iterable<Ticket> getAllTicketBySubCategory(@RequestBody Map<String, String> data){
				
				return ticketService.findAllTicketsBySubCategory(Integer.parseInt(data.get("sub_category_id")),
						data.get("employeeId"));
			}		
			
	
	

}
