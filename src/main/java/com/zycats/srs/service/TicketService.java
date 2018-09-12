package com.zycats.srs.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.Employee;
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
	public Ticket add(Ticket ticket) {
		ticket.setDatetime(new Timestamp(new java.util.Date().getTime()));
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
	
	@Override
	public Iterable<Ticket> getTicketsByStatusAndEngineer(Status status , String engineerId){
		
	try{
		Employee employee = employeeService.getEmployeeById(engineerId);
		return ticketRepository.findAllTicketsByStatusAndEngineer(status, employee);
	}
	catch(IllegalArgumentException e){
		return null;
	}
	
	}
}
