package com.zycats.srs.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "TBL_COMMENT")
public class Comment {

	@Id
	@GeneratedValue
	private int id;
	private String message;

	@ManyToOne
	@JoinColumn(name = "comment_by")
	private Employee commentBy;

	private Timestamp datetime;
	private Status statusFrom;
	private Status statusTo;

	@ManyToOne
	@JoinColumn(name = "ticket_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Ticket ticket;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Employee getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(Employee commentBy) {
		this.commentBy = commentBy;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
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

	@JsonIgnore
	public Ticket getTicket() {
		return ticket;
	}

	@JsonProperty
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", message=" + message + ", commentBy=" + commentBy + ", datetime=" + datetime
				+ ", statusFrom=" + statusFrom + ", statusTo=" + statusTo + ", ticket=" + ticket + "]";
	}

}
