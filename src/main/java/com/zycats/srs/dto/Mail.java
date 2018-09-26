package com.zycats.srs.dto;

import java.util.List;

import com.zycats.srs.entity.Employee;

public class Mail {

	private Employee sender;
	private Employee reciever;
	private List<Employee> cc;
	private List<Employee> bcc;
	private String subject;
	private String body;

	public Employee getSender() {
		return sender;
	}

	public void setSender(Employee sender) {
		this.sender = sender;
	}

	public Employee getReciever() {
		return reciever;
	}

	public void setReciever(Employee reciever) {
		this.reciever = reciever;
	}

	public List<Employee> getCc() {
		return cc;
	}

	public void setCc(List<Employee> cc) {
		this.cc = cc;
	}

	public List<Employee> getBcc() {
		return bcc;
	}

	public void setBcc(List<Employee> bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Mail [sender=" + sender + ", reciever=" + reciever + ", cc=" + cc + ", bcc=" + bcc + ", subject="
				+ subject + ", body=" + body + ", getSender()=" + getSender() + ", getReciever()=" + getReciever()
				+ ", getCc()=" + getCc() + ", getBcc()=" + getBcc() + ", getSubject()=" + getSubject() + ", getBody()="
				+ getBody() + "]";
	}

}
