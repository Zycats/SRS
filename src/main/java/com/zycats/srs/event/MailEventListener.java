package com.zycats.srs.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.zycats.srs.service.EmailService;

@Component
public class MailEventListener implements ApplicationListener<MailEvent> {

	@Autowired
	private EmailService emailService;

	@Override
	public void onApplicationEvent(MailEvent event) {
		switch (event.getMailType()) {
		case NEW_SRS:
			emailService.newSRSMail(event.getMail());
		}
	}

}
