package com.zycats.srs.event;

import org.springframework.context.ApplicationEvent;

import com.zycats.srs.dto.Mail;
import com.zycats.srs.entity.MailType;

public class MailEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5796774977023246345L;
	private Mail mail;
	private MailType mailType;

	public MailEvent(Object source, Mail mail, MailType mailType) {
		super(source);
		this.mail = mail;
		this.mailType = mailType;
	}

	public Mail getMail() {
		return mail;
	}

	public MailType getMailType() {
		return mailType;
	}
}
