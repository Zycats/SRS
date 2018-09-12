package com.zycats.srs.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.repository.TicketRepository;

@Service
public class TicketService implements ITicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private IEmployeeService employeeService;

	@Override
	public Ticket add(Ticket ticket, Authentication auth, String machineIp) {
		ticket.setDatetime(new Timestamp(new java.util.Date().getTime()));
		ticket.setEmployee(employeeService.getEmployee(auth.getName(), machineIp));
		ticket.setStatus(Status.OPEN);
		return ticketRepository.save(ticket);
	}

	@Override
	public Iterable<Ticket> getAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getById(int id) {
		return ticketRepository.findById(id).get();
	}

	@Override
	public boolean delete(int id) {
		try {
			ticketRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
}
