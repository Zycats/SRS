package com.zycats.srs.service;

import org.springframework.security.core.Authentication;

import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;

public interface ITicketService {

	Iterable<Ticket> getAll();

	Ticket getById(int id);

	boolean delete(int id);

	Ticket add(Ticket ticket, Authentication auth, String machineIp);
	
	///-- services related to Engineer ----////
	
	Iterable<Ticket> findAllTicketsByEngineer(String employeeId);
	
	Iterable<Ticket> findAllTicketsByStatusEngineer(Status status, String engineerId);

	Iterable<Ticket> findAllTicketsByCategoryEngineer(int category_id, String engineerId);

	Iterable<Ticket> findAllTicketsBySubCategoryEngineer(int sub_category_id, String engineerId);
	
	
	// -- services related to Employee -----///
	Iterable<Ticket> findAllTicketsByEmployee(String employeeId);
	
	Iterable<Ticket> findAllTicketsByStatusEmployee(Status status, String employeeId);
	
	Iterable<Ticket> findAllTicketsByCategoryEmployee(int category_id, String engineerId);

	Iterable<Ticket> findAllTicketsBySubCategoryEmployee(int sub_category_id, String employeeId);
	
	
	// general method For Employee
	
	Integer getNoOfIsseuesByStatusEmployee(Status status,String employeeId); 
	
	
	//general method for Engineer
	
	Object getNoOfIsseuesByStatusEngineer();
}