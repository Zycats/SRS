package com.zycats.srs.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zycats.srs.entity.Ticket;

public interface TicketRepositoryPageable extends TicketRepository {

	@Query("SELECT t FROM Ticket t")
	Iterable<Ticket> findAllTicketsPageable(PageRequest pageRequest);

	@Modifying
	@Query("UPDATE Ticket t SET t = :ticket WHERE t.status = com.zycats.srs.entity.Status.OPEN")
	int setAssign(@Param("ticket") Ticket ticket);
}
