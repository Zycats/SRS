package com.zycats.srs.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.dto.EmployeeLocationDTO;
import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.service.IEmployeeService;
import com.zycats.srs.service.ITicketService;

@RestController
@RequestMapping("/rest/employee/*")
public class EmployeeRestController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private ITicketService ticketService;

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Employee getEmployee(HttpServletRequest request, Authentication auth) {
		return employeeService.getEmployee(auth.getName(), request.getRemoteAddr());
	}

	@RequestMapping(value = "get/employee-location", method = RequestMethod.GET)
	public EmployeeLocationDTO getEmployeeLocation(HttpServletRequest request, Authentication auth) {
		return employeeService.getEmployeeLocation(auth.getName(), request.getRemoteAddr());
	}

	@RequestMapping(value = "get/all", method = RequestMethod.GET)
	public Iterable<Employee> getAllEmployees() {
		return employeeService.allEmployees();
	}

	@RequestMapping(value = "post", method = RequestMethod.POST)
	public Employee setFirstLoginEmployee(@RequestBody Employee employee, Authentication auth) {
		return employeeService.update(employee, auth);
	}

	@RequestMapping(value = "get/auth", method = RequestMethod.GET)
	public Authentication getAuth(Authentication auth) {
		return auth;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public boolean deleteEmployee(Authentication auth) {
		return employeeService.deleteEmployee(auth);
	}

	// ticket related rest controls

	// accepts status and employeeId and returns all the tickets
	@RequestMapping(
					value = "get/status/empId",
					method = RequestMethod.POST,
					consumes = "application/json",
					produces = "application/json")
	public Iterable<Ticket> getAllTicketByStatus(@RequestBody Map<String, String> data) {

		return ticketService.getTicketsByStatusAndEngineer(Status.valueOf(data.get("status")), data.get("employeeId"));
	}

	// accepts category and employeeId and returns all the tickets
	@RequestMapping(
					value = "get/category/empId",
					method = RequestMethod.POST,
					consumes = "application/json",
					produces = "application/json")
	public Iterable<Ticket> getAllTicketByCategory(@RequestBody Map<String, String> data) {

		return ticketService
				.findAllTicketsByCategory(Integer.parseInt(data.get("category_id")), data.get("employeeId"));
	}

	// accepts sub_category and employeeId and returns all the tickets
	@RequestMapping(
					value = "get/sub_category/empId",
					method = RequestMethod.POST,
					consumes = "application/json",
					produces = "application/json")
	public Iterable<Ticket> getAllTicketBySubCategory(@RequestBody Map<String, String> data) {

		return ticketService
				.findAllTicketsBySubCategory(Integer.parseInt(data.get("sub_category_id")), data.get("employeeId"));
	}

	// accepts employeeId and returns all the tickets
	@RequestMapping(value = "get/empId", produces = "application/json")
	public Iterable<Ticket> getAllTicketByEmployee(Authentication auth) {

		return ticketService.findAllTicketsByEmployee(auth.getName());
	}

}
