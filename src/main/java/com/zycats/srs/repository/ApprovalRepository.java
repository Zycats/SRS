package com.zycats.srs.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zycats.srs.entity.Approval;

public interface ApprovalRepository extends CrudRepository<Approval, Integer> {

	@Query("SELECT a FROM Approval a WHERE a.approver.id = :employeeId")
	Iterable<Approval> pendingApprovals(@Param("employeeId") String employeeId);

	@Query("SELECT a from Approval a WHERE a.approver.id = (SELECT md.manager.id FROM ManagerDelegate md WHERE md.delegate.id = :employeeId)")
	Iterable<Approval> pendingApprovalsDelegated(@Param("employeeId") String employeeId);

}
