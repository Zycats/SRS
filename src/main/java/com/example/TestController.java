package com.example;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping
	public String test(Authentication auth, HttpServletRequest request) {
		// System.out.println("amhi print kela : " + request.getRemoteAddr());
		return String.format(
				"You are logged in as: %s, %s, %s, %s",
				auth.getPrincipal(),
				auth.getName(),
				auth.getCredentials(),
				auth.isAuthenticated());

	}

}
