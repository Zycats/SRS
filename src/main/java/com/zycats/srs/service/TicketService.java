package com.zycats.srs.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zycats.srs.dto.Mail;
import com.zycats.srs.entity.Approval;
import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.MailType;
import com.zycats.srs.entity.Role;
import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.event.MailEvent;
import com.zycats.srs.exception.InsufficientPriviledgesException;
import com.zycats.srs.repository.TicketRepositoryPageable;

@Service
public class TicketService<ticketRepositoryPageable> implements ITicketService {

	@Autowired
	private TicketRepositoryPageable ticketRepository;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IApprovalService approvalService;

	@Autowired
	private IIssueSubCategoryService subCategoryService;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	@Transactional
	@CacheEvict(cacheNames = "{noOfTicketsEngineer, noOfTicketsEmployee}", allEntries = true)
	public Ticket add(Ticket ticket, Authentication auth, String machineIp) {
		ticket.setDatetime(new Timestamp(new java.util.Date().getTime()));
		ticket.setEmployee(employeeService.getEmployee(auth.getName(), machineIp));

		ticket.setSubCategory(subCategoryService.getById(ticket.getSubCategory().getId()));

		if (ticket.getSubCategory().isRequiresApproval()) {
			// The ticket requires approval

			ticket.setStatus(Status.PENDING_APPROVAL);

			ticket = ticketRepository.save(ticket);

			Mail mail = new Mail();
			mail.setReciever(ticket.getEmployee());
			mail.setTicket(ticket);

			// Approval Table Entry
			Approval approval = new Approval();
			approval.setApprover(ticket.getApprover());
			approval.setTicket(ticket);
			approval.setRaisedOn(new Timestamp(new java.util.Date().getTime()));
			approval.setIsApproved(false);

			approvalService.add(approval);

			applicationEventPublisher.publishEvent(new MailEvent(this, mail, MailType.APPROVAL_REQUEST));

			// Event to send ACKNOWLEDGEMENT mail to employee
			applicationEventPublisher.publishEvent(new MailEvent(this, mail, MailType.SENT_FOR_APPROVAL));

		} else {
			// The ticket is ready to be processed and assigned to a engineer

			ticket.setStatus(Status.OPEN);

			ticket = ticketRepository.save(ticket);

			Mail mail = new Mail();
			mail.setReciever(ticket.getEmployee());
			mail.setTicket(ticket);

			// Event to send ACKNOWLEDGEMENT mail to employee
			applicationEventPublisher.publishEvent(new MailEvent(this, mail, MailType.NEW_SRS));
		}

		return ticket;

	}

	@Override
	public Iterable<Ticket> getAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getById(int id) {
		return ticketRepository.findById(id).get();
	}

	@Override
	public boolean delete(int id) {
		ticketRepository.deleteById(id);
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
		Employee engineer = employeeService.getEmployeeById(engineerId);
		return ticketRepository.findAllTicketsByEngineer(engineer);
	}

	@Override
	public Iterable<Ticket> findAllTicketsByStatusEngineer(Status status, String engineerId) {
		Employee engineer = employeeService.getEmployeeById(engineerId);
		return ticketRepository.findAllTicketsByStatusAndEngineer(status, engineer);
	}

	@Override
	public Iterable<Ticket> findAllTicketsByStatus(Status status) {
		return ticketRepository.findAllTicketsByStatus(status);
	}

	@Override
	public Iterable<Ticket> findAllTicketsByCategoryEngineer(int category_id, String engineerId) {
		Employee engineer = employeeService.getEmployeeById(engineerId);
		return ticketRepository.findAllTicketsByCategoryEngineer(category_id, engineer);

	}

	@Override
	public Iterable<Ticket> findAllTicketsBySubCategoryEngineer(int sub_category_id, String engineerId) {
		Employee engineer = employeeService.getEmployeeById(engineerId);
		return ticketRepository.findAllTicketsBySubCategoryEngineer(sub_category_id, engineer);

	}

	/// --------------- Employee Services --------------////////////

	@Override
	public Iterable<Ticket> findAllTicketsByEmployee(String employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ticketRepository.findAllTicketsByEmployee(employee);
	}

	@Override
	public Iterable<Ticket> findAllTicketsByStatusEmployee(Status status, String employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ticketRepository.findAllTicketsByStatusAndEngineer(status, employee);
	}

	@Override
	public Iterable<Ticket> findAllTicketsByCategoryEmployee(int category_id, String employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ticketRepository.findAllTicketsByCategoryEngineer(category_id, employee);
	}

	@Override
	public Iterable<Ticket> findAllTicketsBySubCategoryEmployee(int sub_category_id, String employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ticketRepository.findAllTicketsBySubCategoryEngineer(sub_category_id, employee);
	}

	// returns total no. of issues / tickets
	@Override
	public Object getNoOfIssues() {
		return ticketRepository.getNoOfTickets();
	}

	@Override
	public Object getNoOfTicketsByStatus(Status status) {
		return ticketRepository.getNoOfTicketsByStatus(status);
	}

	@Override
	public Object getNoOfIssues(String employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ticketRepository.getNoOfTicketsByEmployee(employee);
	}

	// general service for Employee
	@Override
	@Cacheable(value = "noOfTicketsEmployee")
	public Object getNoOfIssuesByStatusEmployee(Status status, String employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ticketRepository.getNoOfTicketsByStatusEmployee(status, employee);
	}

	// ------------------------------------------------------------------------------------------------///////

	// general service for Engineer
	@Override
	@Cacheable(value = "noOfTicketsEngineer")
	public Object getNoOfIssuesByStatusEngineer(Status status, String employeeId) {
		Employee engineer = employeeService.getEmployeeById(employeeId);
		return ticketRepository.getNoOfTicketsByStatusEngineer(status, engineer);
	}

	@Override
	public Ticket update(Ticket ticket, Authentication auth) throws InsufficientPriviledgesException {
		Employee employee = employeeService.getEmployeeById(EmployeeService.getIdFromAuth(auth.getName()));
		if (!(employee.getRole().equals(Role.EXECUTIVE))) {
			throw new InsufficientPriviledgesException(employee,
					"Only " + Role.EXECUTIVE + " is allowed to update ticket");
		}
		System.out.println(ticket);
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket update(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	// get tickets by category [FOR EXECUTIVE]
	@Override
	public Iterable<Ticket> findAllTicketsByCategory(int category_id) {
		return ticketRepository.findAllTicketsByCategory(category_id);
	}

	// get tickets by sub-category [FOR EXECUTIVE]
	@Override
	public Iterable<Ticket> findAllTicketsBySubCategory(int sub_category_id) {
		return ticketRepository.findAllTicketsBySubCategory(sub_category_id);
	}

	// get tickets by category [for Engineer's NEW ISSUES view only]
	@Override
	public Iterable<Ticket> findAllTicketsByCategoryStatus(int category_id, Status status) {
		return ticketRepository.findAllTicketsByCategoryStatus(category_id, status);
	}

	// get tickets by sub-category [for Engineer's NEW ISSUES view only]
	@Override
	public Iterable<Ticket> findAllTicketsBySubCategoryStatus(int sub_category_id, Status status) {
		return ticketRepository.findAllTicketsBySubCategoryStatus(sub_category_id, status);
	}

	@Transactional
	@Override
	public Ticket setAssigned(Ticket ticket, String idFromAuth) {
		ticket.setStatus(Status.WORKING);
		ticket.setEngineer(employeeService.getEmployeeById(idFromAuth));
		ticketRepository.setAssign(ticket.getEngineer().getId(), ticket.getId());
		return null;
	}

}
