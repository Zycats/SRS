package com.zycats.srs.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

	@Override
	public Iterable<Ticket> getTicketsByStatusAndEngineer(Status status, String engineerId) {

		try {
			Employee employee = employeeService.getEmployeeById(engineerId);
			return ticketRepository.findAllTicketsByStatusAndEngineer(status, employee);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Override
	public Iterable<Ticket> findAllTicketsByCategory(int category_id, String engineerId) {

		try {
			Employee employee = employeeService.getEmployeeById(engineerId);
			return ticketRepository.findAllTicketsByCategory(category_id, employee);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Override
	public Iterable<Ticket> findAllTicketsBySubCategory(int sub_category_id, String engineerId) {

		try {
			Employee employee = employeeService.getEmployeeById(engineerId);
			return ticketRepository.findAllTicketsBySubCategory(sub_category_id, employee);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Override
	public Iterable<Ticket> findAllTicketsByEmployee(String employeeId) {

		try {
			employeeId = EmployeeService.getIdFromAuth(employeeId);
			Employee employee = employeeService.getEmployeeById(employeeId);
			return ticketRepository.findAllTicketsByEmployee(employee);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

}
