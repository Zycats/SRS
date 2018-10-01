package com.zycats.srs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.Approval;
import com.zycats.srs.repository.ApprovalRepository;

@Service
public class ApprovalService implements IApprovalService {

	@Autowired
	private ApprovalRepository approvalRepository;

	@Override
	public Approval add(Approval approval) {
		return approvalRepository.save(approval);
	}

	@Override
	public Iterable<Approval> getAll() {
		return approvalRepository.findAll();
	}

	@Override
	public Approval getById(int id) {
		return approvalRepository.findById(id).get();
	}

	@Override
	public boolean delete(int id) {
		approvalRepository.deleteById(id);
		return true;
	}

	// Pending approvals for currently authenticated user
	@Override
	public Iterable<Approval> pendingApprovals(String employeeId) {
		return approvalRepository.pendingApprovals(employeeId);
	}

	@Override
	public Iterable<Approval> pendingApprovalsDelegated(String employeeId) {
		return approvalRepository.pendingApprovalsDelegated(employeeId);
	}
}
