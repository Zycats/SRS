package com.zycats.srs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.OSType;

@RestController
public class TestController {

	@RequestMapping
	public String test(Authentication auth, HttpServletRequest request) {
		// System.out.println("amhi print kela : " + request.getRemoteAddr());
		return String.format(
				"You are logged in as: %s, %s, %s, %s",
				auth.getDetails(),
				auth.getName(),
				auth.getCredentials(),
				auth.isAuthenticated());

	}

	@RequestMapping("/meta")
	public OSType[] getMeta(Authentication auth) {
		return OSType.values();
	}

}
