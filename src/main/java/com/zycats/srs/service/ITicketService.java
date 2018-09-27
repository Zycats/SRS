package com.zycats.srs.service;

import org.springframework.security.core.Authentication;

import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.exception.InsufficientPriviledgesException;

public interface ITicketService {

	Iterable<Ticket> getAll();

	Ticket getById(int id);

	boolean delete(int id);

	Ticket add(Ticket ticket, Authentication auth, String machineIp);

	Ticket update(Ticket ticket, Authentication auth) throws InsufficientPriviledgesException;

	/// -- services related to Engineer ----////

	Iterable<Ticket> findAllTicketsByEngineer(String employeeId);

	Iterable<Ticket> findAllTicketsByStatusEngineer(Status status, String engineerId);

	Iterable<Ticket> findAllTicketsByCategoryEngineer(int category_id, String engineerId);

	Iterable<Ticket> findAllTicketsBySubCategoryEngineer(int sub_category_id, String engineerId);

	// -------Paging Queries ------------
	Iterable<Ticket> getAllTicketsPageable(int page, int size);

	// -- services related to Employee -----///
	Iterable<Ticket> findAllTicketsByEmployee(String employeeId);

	Iterable<Ticket> findAllTicketsByStatusEmployee(Status status, String employeeId);

	Iterable<Ticket> findAllTicketsByCategoryEmployee(int category_id, String engineerId);

	Iterable<Ticket> findAllTicketsBySubCategoryEmployee(int sub_category_id, String employeeId);

	////////////////////////////

	Object getNoOfIssues(String employeeId);

	Object getNoOfIssues();
	
	Object getNoOfTicketsByStatus(Status status);

	////////////////////////////
	// Count methods For Employee

	Object getNoOfIssuesByStatusEmployee(Status status, String employeeId);

	// general methods for Engineer

	Object getNoOfIssuesByStatusEngineer(Status status, String employeeId);

	Iterable<Ticket> findAllTicketsByCategory(int category_id);

	Iterable<Ticket> findAllTicketsBySubCategory(int sub_category_id);

	Iterable<Ticket> findAllTicketsByCategoryStatus(int category_id, Status status);

	Iterable<Ticket> findAllTicketsBySubCategoryStatus(int sub_category_id, Status status);

	Iterable<Ticket> findAllTicketsByStatus(Status status);

	Ticket setAssigned(Ticket ticket, String idFromAuth);

	Ticket update(Ticket ticket);

}