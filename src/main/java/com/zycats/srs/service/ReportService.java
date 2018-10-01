package com.zycats.srs.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.Comment;
import com.zycats.srs.entity.Report;
import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.repository.ReportRepository;

@Service
public class ReportService implements IReportService {

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private ITicketService ticketService;
	
	@Autowired
	private IEmployeeService employeeService;

	@Override
	public Iterable<Report> getAllReportsByDate(Timestamp fromDate, Timestamp toDate) {

		return reportRepository.getReportsByDate(fromDate, toDate);

	}

	@Override
	public void addReport(Report report) {
		reportRepository.save(report);
	}

	@Override
	public void removeAllReports() {

		reportRepository.deleteAll();
	}

	@Override
	public void createReports() {

		removeAllReports();

		for (Ticket ticket : ticketService.getAll()) {

			Timestamp statusFromTime = null;
			Timestamp statusToTime = null;
			
			List<Comment> ticketComments = ticket.getComments().stream()
					.sorted((c1, c2) -> c1.getDatetime().compareTo(c2.getDatetime())).collect(Collectors.toList());
			boolean initial = true; // to separate initial comment which can
									// start with Open OR PendingApproval
									// Status
			for (Comment comment : ticketComments) {
				System.out.println(comment.getTicket().getId() + " || " + comment.getDatetime().toString());

				
				Report report = new Report();

				if ((comment.getStatusFrom().equals(Status.OPEN))
						|| (comment.getStatusFrom().equals(Status.PENDING_APPROVAL))) {
					if (initial) {
						statusFromTime = ticket.getDatetime();
						initial = false;
						System.out.println("INSIDE IF");
					} else {
						statusFromTime = statusToTime;
					}
				}
				else{
					statusFromTime = statusToTime;
				}
				
			
				statusToTime = comment.getDatetime();

				report.setStatusFrom(comment.getStatusFrom());
				report.setStatusTo(comment.getStatusTo());

				report.setStatusFromTime(statusFromTime);
				report.setStatusToTime(statusToTime);

				report.setComment(comment);

				report.setExecutive(ticket.getEngineer());

				report.setEmployee(ticket.getEmployee());

				report.setReportingManager(ticket.getEmployee().getReportingManager());

				report.setCommentByRole(comment.getCommentBy().getRole());

				report.setTicket(ticket);

				reportRepository.save(report);

			}

			System.out.println("-----------------------------------------------------");
		}

	}

	
	// provide date from to to and fetch the list
	
		//----Reports By Ticket
	@Override
	public Iterable<Report> getReportsByTicketAndDate(int ticket_id,String fromDate, String toDate) {
		return	reportRepository.getReportsByTicketAndDate(ticketService.getById(ticket_id), 
																Timestamp.valueOf(fromDate), 
																Timestamp.valueOf(toDate));
	}
	
	@Override
	public Iterable<Report> getReportsByTicket(int ticket_id) {
		return	reportRepository.getReportsByTicket(ticketService.getById(ticket_id));
	}

		//----Reports By Executives
	@Override
	public Iterable<Report> getReportsByExecutiveAndDate(String executive_id,String fromDate, String toDate) {
		
		return reportRepository.getReportsByExecutiveAndDate(employeeService.getEmployeeById(executive_id), 
																Timestamp.valueOf(fromDate), 
																Timestamp.valueOf(toDate));
		
	}
	

}
