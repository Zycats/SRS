package com.zycats.srs.service;

import org.springframework.security.core.Authentication;

import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;

public interface ITicketService {

	Iterable<Ticket> getAll();

	Ticket getById(int id);

	boolean delete(int id);

	Iterable<Ticket> getTicketsByStatusAndEngineer(Status status, String engineerId);

	Iterable<Ticket> findAllTicketsByCategory(int category_id, String engineerId);

	Iterable<Ticket> findAllTicketsBySubCategory(int sub_category_id, String engineerId);

	Ticket add(Ticket ticket, Authentication auth, String machineIp);

	Iterable<Ticket> findAllTicketsByEmployee(String employeeId);

}