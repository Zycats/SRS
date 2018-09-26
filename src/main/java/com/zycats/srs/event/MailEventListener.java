package com.zycats.srs.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MailEventListener implements ApplicationListener<MailEvent> {

	@Override
	public void onApplicationEvent(MailEvent event) {
		System.out.println(event.getMail());

	}

}
