package com.zycats.srs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zycats.srs.service.IEmployeeService;

@Controller
public class ViewController {

	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping("/")
	public String auth(Authentication auth, HttpServletRequest request, Model model) {

		switch (employeeService.getEmployee(auth.getName(), request.getRemoteAddr()).getRole().toString()) {
		case "EMPLOYEE":
			model.addAttribute("role", "employee");
			return "home";
		case "EXECUTIVE":
			model.addAttribute("role", "executive");
			return "home";
		case "MANAGER":
			model.addAttribute("role", "manager");
			return "home";
		case "ADMIN":
			model.addAttribute("role", "admin");
			return "home";
		default:
			return "Invalid role!";
		}
	}

	/*
	 * @RequestMapping("/login") public String customLogin(){ return "login"; }
	 */

}
