package com.zycats.srs.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_APPROVAL")
public class Approval {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;

	@ManyToOne
	@JoinColumn(name = "approver_id")
	private Employee approver;

	private Timestamp raisedOn;
	private Boolean isApproved;
	private Timestamp approvedOn;
	private Timestamp expiry;
	private String message;

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

	public Employee getApprover() {
		return approver;
	}

	public void setApprover(Employee approver) {
		this.approver = approver;
	}

	public Timestamp getRaisedOn() {
		return raisedOn;
	}

	public void setRaisedOn(Timestamp raisedOn) {
		this.raisedOn = raisedOn;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Timestamp getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Timestamp approvedOn) {
		this.approvedOn = approvedOn;
	}

	public Timestamp getExpiry() {
		return expiry;
	}

	public void setExpiry(Timestamp expiry) {
		this.expiry = expiry;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Approval [id=" + id + ", ticket=" + ticket + ", approver=" + approver + ", raisedOn=" + raisedOn
				+ ", isApproved=" + isApproved + ", approvedOn=" + approvedOn + ", expiry=" + expiry + ", message="
				+ message + "]";
	}

}
