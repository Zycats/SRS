package com.zycats.srs.service;

import com.zycats.srs.entity.Ticket;

public interface ITicketService {

	Ticket add(Ticket ticket);

	Iterable<Ticket> getAll();

	Ticket getById(int id);

	boolean delete(int id);

}