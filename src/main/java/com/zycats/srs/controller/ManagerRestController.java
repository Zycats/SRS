package com.zycats.srs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Approval;
import com.zycats.srs.service.EmployeeService;
import com.zycats.srs.service.IApprovalService;

@RestController
@RequestMapping("/rest/manager/*")
public class ManagerRestController {

	@Autowired
	private IApprovalService approvalService;

	// gets all the pending approvals for the current authenticated manager
	@RequestMapping(value = "get/approval/all", produces = "application/json")
	public Iterable<Approval> getAllPendingApprovals(Authentication auth) {

		return approvalService.pendingApprovals(EmployeeService.getIdFromAuth(auth.getName()));
	}

	// gets all the pending approvals for the current authenticated manager being
	// delegated
	@RequestMapping(value = "get/approval/delegate", produces = "application/json")
	public Iterable<Approval> getAllPendingApprovalsDelegated(Authentication auth) {

		return approvalService.pendingApprovalsDelegated(EmployeeService.getIdFromAuth(auth.getName()));
	}
}