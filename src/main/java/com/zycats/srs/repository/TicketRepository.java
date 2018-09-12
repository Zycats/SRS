package com.zycats.srs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

	 @Query(value = "SELECT t FROM Ticket as t WHERE t.status = :enumStatus AND t.engineer = :engineer")
	  List<Ticket> findAllTicketsByStatusAndEngineer(@Param("enumStatus") Status status,
			  @Param("engineer") Employee engineer);
	 
	
	
}
