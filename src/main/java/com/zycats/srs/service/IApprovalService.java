package com.zycats.srs.service;

import com.zycats.srs.entity.Approval;

public interface IApprovalService {

	Approval add(Approval approval);

	Iterable<Approval> getAll();

	Approval getById(int id);

	boolean delete(int id);

	Iterable<Approval> pendingApprovals(String employeeId);

	Iterable<Approval> pendingApprovalsDelegated(String employeeId);

}