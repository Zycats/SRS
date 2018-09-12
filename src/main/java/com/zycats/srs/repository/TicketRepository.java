package com.zycats.srs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

	 @Query(value = "SELECT t FROM Ticket t WHERE t.status = :enumStatus AND t.engineer = :engineer")
	  List<Ticket> findAllTicketsByStatusAndEngineer(@Param("enumStatus") Status status,
			  @Param("engineer") Employee engineer);
	 
	
	 @Query(value = "SELECT t from Ticket t INNER JOIN IssueSubCategory c ON t.subCategory.id = c.id WHERE c.issueCategory.id = :category_id and t.engineer = :engineer")
	 List<Ticket> findAllTicketsByCategory(@Param("category_id") int category_id,
			 @Param("engineer") Employee engineer);
	 
	 
	 @Query(value = "SELECT t from Ticket t  WHERE t.subCategory.id = :sub_category_id AND t.engineer = :engineer")
	 List<Ticket> findAllTicketsBySubCategory(@Param("sub_category_id") int sub_category_id,
			 @Param("engineer") Employee engineer);
}
