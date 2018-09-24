package com.zycats.srs.service;

import java.sql.Timestamp;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Role;
import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.exception.InsufficientPriviledgesException;
import com.zycats.srs.repository.TicketRepositoryPageable;

@Service
public class TicketService<ticketRepositoryPageable> implements ITicketService {

	@Autowired
	private TicketRepositoryPageable ticketRepository;

	@Autowired
	private IEmployeeService employeeService;

	@Override
	@CacheEvict(cacheNames = "{noOfTicketsEngineer,noOfTicketsEmployee}", allEntries = true)
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
		try {
			return ticketRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
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

	/// -------Pageable Queries---------------///
	@Transactional(readOnly = true)
	public Iterable<Ticket> getAllTicketsPageable(int page, int size) {

		// return ticketRepository.findAllTicketsPageable(new PageRequest(page, size,
		// Sort.Direction.DESC, "datetime"));

		Pageable pageable = PageRequest.of(0, 3, Sort.by("datetime").descending());

		while (true) {
			Page<Ticket> pageTicket = ticketRepository.findAll(pageable);
			;
			System.out.println("Page no: " + pageTicket.getNumber());
			pageTicket.getContent().forEach(System.out::println);
			if (!pageTicket.hasNext()) {
				break;
			}
			pageable = pageTicket.nextPageable();

			System.out.println("==============================================================");
		}

		return null;

	}

	/// ----------- Engineer Services ------------------/////////////////

	@Override
	public Iterable<Ticket> findAllTicketsByEngineer(String engineerId) {

		try {
			Employee engineer = employeeService.getEmployeeById(engineerId);
			return ticketRepository.findAllTicketsByEngineer(engineer);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Override
	public Iterable<Ticket> findAllTicketsByStatusEngineer(Status status, String engineerId) {

		try {
			Employee engineer = employeeService.getEmployeeById(engineerId);
			return ticketRepository.findAllTicketsByStatusAndEngineer(status, engineer);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Override
	public Iterable<Ticket> findAllTicketsByStatus(Status status) {

		try {
			return ticketRepository.findAllTicketsByStatus(status);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Override
	public Iterable<Ticket> findAllTicketsByCategoryEngineer(int category_id, String engineerId) {

		try {
			Employee engineer = employeeService.getEmployeeById(engineerId);
			return ticketRepository.findAllTicketsByCategoryEngineer(category_id, engineer);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Override
	public Iterable<Ticket> findAllTicketsBySubCategoryEngineer(int sub_category_id, String engineerId) {

		try {
			Employee engineer = employeeService.getEmployeeById(engineerId);
			return ticketRepository.findAllTicketsBySubCategoryEngineer(sub_category_id, engineer);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	/// --------------- Employee Services --------------////////////

	@Override
	public Iterable<Ticket> findAllTicketsByEmployee(String employeeId) {

		try {
			Employee employee = employeeService.getEmployeeById(employeeId);
			return ticketRepository.findAllTicketsByEmployee(employee);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Override
	public Iterable<Ticket> findAllTicketsByStatusEmployee(Status status, String employeeId) {

		try {
			Employee employee = employeeService.getEmployeeById(employeeId);
			return ticketRepository.findAllTicketsByStatusAndEngineer(status, employee);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Override
	public Iterable<Ticket> findAllTicketsByCategoryEmployee(int category_id, String employeeId) {

		try {
			Employee employee = employeeService.getEmployeeById(employeeId);
			return ticketRepository.findAllTicketsByCategoryEngineer(category_id, employee);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Override
	public Iterable<Ticket> findAllTicketsBySubCategoryEmployee(int sub_category_id, String employeeId) {

		try {
			Employee employee = employeeService.getEmployeeById(employeeId);
			return ticketRepository.findAllTicketsBySubCategoryEngineer(sub_category_id, employee);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	// returns total no. of issues / tickets
	@Override
	public Object getNoOfIssues() {
		return ticketRepository.getNoOfTickets();
	}

	@Override
	public Object getNoOfIssues(String employeeId) {
		try {
			Employee employee = employeeService.getEmployeeById(employeeId);
			return ticketRepository.getNoOfTicketsByEmployee(employee);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	// general service for Employee
	@Override
	@Cacheable(value = "noOfTicketsEmployee")
	public Object getNoOfIssuesByStatusEmployee(Status status, String employeeId) {

		try {
			Employee employee = employeeService.getEmployeeById(employeeId);
			return ticketRepository.getNoOfTicketsByStatusEmployee(status, employee);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	// ------------------------------------------------------------------------------------------------///////

	// general service for Engineer
	@Override
	@Cacheable(value = "noOfTicketsEngineer")
	public Object getNoOfIssuesByStatusEngineer(Status status, String employeeId) {

		try {
			Employee engineer = employeeService.getEmployeeById(employeeId);
			return ticketRepository.getNoOfTicketsByStatusEngineer(status, engineer);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public Ticket update(Ticket ticket, Authentication auth) throws InsufficientPriviledgesException {
		if (!(employeeService.getEmployeeById(EmployeeService.getIdFromAuth(auth.getName())).getRole().equals(
				Role.EXECUTIVE))) {
			throw new InsufficientPriviledgesException("Only " + Role.EXECUTIVE + " is allowed to update ticket");
		}
		System.out.println(ticket);
		return ticketRepository.save(ticket);
	}

	// get tickets by category [FOR EXECUTIVE]
	@Override
	public Iterable<Ticket> findAllTicketsByCategory(int category_id) {

		try {
			return ticketRepository.findAllTicketsByCategory(category_id);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	// get tickets by sub-category [FOR EXECUTIVE]
	@Override
	public Iterable<Ticket> findAllTicketsBySubCategory(int sub_category_id) {

		try {
			return ticketRepository.findAllTicketsBySubCategory(sub_category_id);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	// get tickets by category [for Engineer's NEW ISSUES view only]
	@Override
	public Iterable<Ticket> findAllTicketsByCategoryStatus(int category_id, Status status) {

		try {
			return ticketRepository.findAllTicketsByCategoryStatus(category_id, status);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	// get tickets by sub-category [for Engineer's NEW ISSUES view only]
	@Override
	public Iterable<Ticket> findAllTicketsBySubCategoryStatus(int sub_category_id, Status status) {

		try {
			return ticketRepository.findAllTicketsBySubCategoryStatus(sub_category_id, status);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	@Transactional
	@Override
	public Ticket setAssigned(Ticket ticket, String idFromAuth) {
		ticket.setStatus(Status.WORKING);
		ticket.setEngineer(employeeService.getEmployeeById(idFromAuth));
		ticketRepository.setAssign(ticket);
		return null;
	}

}
