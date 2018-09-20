package com.zycats.srs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Ticket getTicketById(@RequestParam Integer id) {
		return ticketService.getById(id);
	}

	@RequestMapping(value = "get/all", method = RequestMethod.GET)
	public Iterable<Ticket> getAllTicket() {
		return ticketService.getAll();
	}
	
	
	@RequestMapping(value = "get/pageable", method = RequestMethod.GET)
	public Iterable<Ticket> getAllTicketPageable() {
		return ticketService.getAllTicketsPageable(1, 1);
	}

}
