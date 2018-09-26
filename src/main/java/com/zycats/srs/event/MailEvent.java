package com.zycats.srs.event;

import org.springframework.context.ApplicationEvent;

import com.zycats.srs.dto.Mail;

public class MailEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5796774977023246345L;
	private Mail mail;

	public MailEvent(Object source, Mail mail) {
		super(source);
		this.mail = mail;
	}

	public Mail getMail() {
		return mail;
	}
}
