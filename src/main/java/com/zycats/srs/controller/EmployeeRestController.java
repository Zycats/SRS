package com.zycats.srs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Employee;
import com.zycats.srs.service.IEmployeeService;

@RestController
@RequestMapping("rest/employee/*")
public class EmployeeRestController {

	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping("get")
	public Employee getEmployee(HttpServletRequest request, Authentication auth) {
		return employeeService.getEmployee(auth.getName(), request.getRemoteAddr());
	}

	@RequestMapping("get/all")
	public Iterable<Employee> getAllEmployees() {
		return employeeService.allEmployees();
	}

	@RequestMapping(name = "post", method = RequestMethod.POST)
	public Employee setFirstLoginEmployee(@RequestBody Employee employee, Authentication auth) {
		return employeeService.update(employee, auth);
	}

	@RequestMapping("get/auth")
	public Authentication getAuth(Authentication auth) {
		return auth;
	}

	@RequestMapping("delete")
	public boolean deleteEmployee(Authentication auth) {
		return employeeService.deleteEmployee(auth);
	}
}
