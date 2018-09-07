package com.zycats.srs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Ticket;
import com.zycats.srs.service.ITicketService;

@RestController
@RequestMapping("/rest/ticket/*")
public class TicketController {

	private ITicketService ticketService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Ticket addTicket(@RequestBody Ticket ticket) {
		return ticketService.add(ticket);
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

}
