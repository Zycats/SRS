package com.zycats.srs.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;

import com.zycats.srs.entity.Ticket;

public interface TicketRepositoryPageable extends TicketRepository {

	@Query("SELECT t FROM Ticket t")
	Iterable<Ticket> findAllTicketsPageable(PageRequest pageRequest);
}
