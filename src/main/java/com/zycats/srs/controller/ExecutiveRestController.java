package com.zycats.srs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Status;
import com.zycats.srs.entity.Ticket;
import com.zycats.srs.service.EmployeeService;
import com.zycats.srs.service.ITicketService;

@RestController
@RequestMapping("/rest/executive/*")
public class ExecutiveRestController {


	@Autowired
	private ITicketService ticketService;

	// accepts status and employeeId and returns all the tickets
	@RequestMapping(
					value = "get/status/engId",
					method = RequestMethod.POST,
					consumes = "application/json",
					produces = "application/json")
	public Iterable<Ticket> getAllTicketByStatus(@RequestBody Map<String, String> data,Authentication auth) {

		return ticketService.findAllTicketsByStatusEngineer(Status.valueOf(data.get("status")), EmployeeService.getIdFromAuth(auth.getName()));
	}

	// accepts category and employeeId and returns all the tickets
	@RequestMapping(
					value = "get/category/engId",
					method = RequestMethod.POST,
					consumes = "application/json",
					produces = "application/json")
	public Iterable<Ticket> getAllTicketByCategory(@RequestBody Map<String, String> data,Authentication auth) {

		return ticketService
				.findAllTicketsByCategoryEngineer(Integer.parseInt(data.get("category_id")), EmployeeService.getIdFromAuth(auth.getName()));
	}
	
	
	// accepts sub_category and employeeId and returns all the tickets
		@RequestMapping(
						value = "get/sub_category/engId",
						method = RequestMethod.POST,
						consumes = "application/json",
						produces = "application/json")
		public Iterable<Ticket> getAllTicketBySubCategory(@RequestBody Map<String, String> data,Authentication auth) {

			return ticketService
					.findAllTicketsBySubCategoryEngineer(Integer.parseInt(data.get("sub_category_id")), EmployeeService.getIdFromAuth(auth.getName()));
		}

		// accepts employeeId and returns all the tickets
		@RequestMapping(value = "get/engId", produces = "application/json")
		public Iterable<Ticket> getAllTicketByEngineer(Authentication auth) {

			return ticketService.findAllTicketsByEngineer(EmployeeService.getIdFromAuth(auth.getName()));
		}
	
	
		// accepts employeeId and returns all the tickets
			@RequestMapping(value = "get/ticket-no", produces = "application/json")
			public Object getAllTicketNo(Authentication auth) {

				return ticketService.getNoOfIssues();
			}
			
			// accepts employeeId and returns all the tickets having some status
			@RequestMapping(value = "get/status/ticket-no", produces = "application/json")
			public Object getAllTicketNoByStatusEngineer(@RequestBody Map<String, String> data,Authentication auth) {

				return ticketService.getNoOfIssuesByStatusEngineer(Status.valueOf(data.get("status")), EmployeeService.getIdFromAuth(auth.getName()));
			}
			
		
}
