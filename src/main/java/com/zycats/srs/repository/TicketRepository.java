package com.zycats.srs.repository;

import org.springframework.data.repository.CrudRepository;

import com.zycats.srs.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

}
