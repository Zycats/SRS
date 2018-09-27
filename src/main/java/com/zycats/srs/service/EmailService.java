package com.zycats.srs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.zycats.srs.dto.Mail;

@Service
public class EmailService {

	private static final String OFFICIAL_MAIL = "srs@zycus.com";
	private static final String OFFICIAL_MAIL_NAME = "ITIS ZYCUS";

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	private SimpleMailMessage simpleMailMessage;

	public void newSRSMail(Mail mail) {
		// String sender = idToName(mail.getSender().getId());
		String reciever = idToName(mail.getReciever().getId());

		mail.setSubject(
				String.format(
						"Regarding SRS#%s | %s" + ((mail.getTicket().getDescription().length() > 15) ? "..." : "."),
						mail.getTicket().getId(),
						mail.getTicket().getDescription().substring(0, 15)));

		mail.setBody(
				String.format(
						"Hi %s,\n\nNew SRS has been raised successfully.\n\n<strong>%s</strong>\n\nRegards\n%s",
						reciever,
						mail.getTicket().getDescription(),
						OFFICIAL_MAIL_NAME));

		simpleMailMessage.setFrom(OFFICIAL_MAIL); // Engineer not yet assigned
		simpleMailMessage.setTo(mail.getReciever().getEmail());
		simpleMailMessage.setSubject(mail.getSubject());
		simpleMailMessage.setText(mail.getBody());

		try {
			simpleMailMessage.setBcc(mail.getBcc().toArray(new String[mail.getBcc().size()]));
			simpleMailMessage.setCc(mail.getCc().toArray(new String[mail.getCc().size()]));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		System.out.println(simpleMailMessage);
		emailSender.send(simpleMailMessage);
	}

	private static String idToName(String id) {
		try {
			String[] names = id.split("\\.");
			names[0] = names[0].substring(0, 1).toUpperCase() + names[0].substring(1, names[0].length());
			names[1] = names[1].substring(0, 1).toUpperCase() + names[1].substring(1, names[1].length());
			return names[0] + " " + names[1];
		} catch (Exception e) {
			e.printStackTrace();
			return id;
		}
	}
}
