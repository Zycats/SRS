package com.zycats.srs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Ticket;
import com.zycats.srs.service.ITicketService;

@RestController
@RequestMapping("rest/ticket/*")
public class TicketController {

	private ITicketService ticketService;

	@RequestMapping(name = "add")
	public Ticket addTicket(@RequestBody Ticket ticket) {
		return ticketService.add(ticket);
	}

	@RequestMapping(name = "delete/{id}")
	public boolean addTicket(@PathVariable int id) {
		return ticketService.delete(id);
	}

	@RequestMapping(name = "get/{id}")
	public Ticket getTicketById(@PathVariable int id) {
		return ticketService.getById(id);
	}

	@RequestMapping(name = "get/all")
	public Iterable<Ticket> getAllTicket() {
		return ticketService.getAll();
	}

}
