package com.zycats.srs.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_REPORT")
public class Report {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;
	
	private Status statusFrom;
	private Timestamp statusFromTime;
	
	private Status statusTo;
	private Timestamp statusToTime;
	
	@ManyToOne
	@JoinColumn(name = "reporting_manager_id")
	private Employee reportingManager;
	
	@ManyToOne
	@JoinColumn(name = "executive_id")
	private Employee executive;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@OneToOne
	@JoinColumn(name = "comment_id")
	private Comment comment;
	
	
	private Role commentByRole;
	
	
	public Timestamp getStatusFromTime() {
		return statusFromTime;
	}

	public void setStatusFromTime(Timestamp statusFromTime) {
		this.statusFromTime = statusFromTime;
	}

	public Timestamp getStatusToTime() {
		return statusToTime;
	}

	public void setStatusToTime(Timestamp statusToTime) {
		this.statusToTime = statusToTime;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Status getStatusFrom() {
		return statusFrom;
	}

	public void setStatusFrom(Status statusFrom) {
		this.statusFrom = statusFrom;
	}

	public Status getStatusTo() {
		return statusTo;
	}

	public void setStatusTo(Status statusTo) {
		this.statusTo = statusTo;
	}

	public Employee getExecutive() {
		return executive;
	}

	public void setExecutive(Employee executive) {
		this.executive = executive;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Employee getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(Employee reportingManager) {
		this.reportingManager = reportingManager;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Role getCommentByRole() {
		return commentByRole;
	}

	public void setCommentByRole(Role commentByRole) {
		this.commentByRole = commentByRole;
	}	
	
	
}
