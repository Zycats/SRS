package com.zycats.srs.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.dto.EmployeeLocationDTO;
import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.service.EmployeeService;
import com.zycats.srs.service.IEmployeeService;
import com.zycats.srs.service.ITicketService;

@RestController
@RequestMapping("/rest/employee/*")
public class EmployeeRestController {

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private ITicketService ticketService ;

	@MessageMapping("/employee/get")
	@SendTo("topic/employee/observe")
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Employee getEmployee(HttpServletRequest request, Authentication auth) {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	public Iterable<Ticket> getAllTicketByStatus(@RequestBody Map<String, String> data, Authentication auth) {

		return ticketService.findAllTicketsByStatusEmployee(Status.valueOf(data.get("status")),EmployeeService.getIdFromAuth(auth.getName()));
	}

	// accepts category and employeeId and returns all the tickets
	@RequestMapping(
					value = "get/category/empId",
					method = RequestMethod.POST,
					consumes = "application/json",
					produces = "application/json")
	public Iterable<Ticket> getAllTicketByCategory(@RequestBody Map<String, String> data, Authentication auth) {

		return ticketService
				.findAllTicketsByCategoryEmployee(Integer.parseInt(data.get("category_id")), EmployeeService.getIdFromAuth(auth.getName()));
	}

	// accepts sub_category and employeeId and returns all the tickets
	@RequestMapping(
					value = "get/sub_category/empId",
					method = RequestMethod.POST,
					consumes = "application/json",
					produces = "application/json")
	public Iterable<Ticket> getAllTicketBySubCategory(@RequestBody Map<String, String> data,Authentication auth) {

		return ticketService
				.findAllTicketsBySubCategoryEmployee(Integer.parseInt(data.get("sub_category_id")), EmployeeService.getIdFromAuth(auth.getName()));
	}

	// accepts employeeId and returns all the tickets
	@RequestMapping(value = "get/empId", produces = "application/json")
	public Iterable<Ticket> getAllTicketByEmployee(Authentication auth) {

		return ticketService.findAllTicketsByEmployee(EmployeeService.getIdFromAuth(auth.getName()));
	}
	
	
	// accepts employeeId and returns no. of the tickets
			@RequestMapping(value = "get/ticket-no", produces = "application/json")
			public Object getAllTicketNo(Authentication auth) {

				return ticketService.getNoOfIssues(EmployeeService.getIdFromAuth(auth.getName()));
			}
			
			// accepts employeeId and returns no. of the tickets having some status
			@RequestMapping(value = "get/status/ticket-no", produces = "application/json")
			public Object getAllTicketNoByStatusEmployee(@RequestBody Map<String, String> data,Authentication auth) {

				return ticketService.getNoOfIssuesByStatusEmployee(Status.valueOf(data.get("status")), EmployeeService.getIdFromAuth(auth.getName()));
			}

}
