package com.zycats.srs.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Report;
import com.zycats.srs.entity.Ticket;

public interface ReportRepository extends PagingAndSortingRepository<Report, Integer> {

	
	//--Queries which take date from and to and return report
	
	@Query(value = "SELECT r "
				+ "FROM Report r "
				+ "WHERE r.statusFromTime BETWEEN :fromDate AND :toDate "
				+ "OR "
				+ "r.statusToTime BETWEEN :fromDate AND :toDate "
				+ "ORDER BY r.statusToTime")
	public List<Report> getReportsByDate(@Param("fromDate") Timestamp fromDate, @Param("toDate") Timestamp toDate);
	
	
	@Query(value = "SELECT r "
			+ "FROM Report r "
			+ "WHERE (r.statusFromTime BETWEEN :fromDate AND :toDate "
			+ "OR "
			+ "r.statusToTime BETWEEN :fromDate AND :toDate) "
			+ "AND r.ticket = :ticket "
			+ "ORDER BY r.statusToTime")
	public List<Report> getReportsByTicketAndDate(@Param("ticket") Ticket ticket ,@Param("fromDate") Timestamp fromDate, @Param("toDate") Timestamp toDate);
	
	@Query(value = "SELECT r "
			+ "FROM Report r "
			+ "WHERE "
			+ "r.ticket = :ticket "
			+ "ORDER BY r.statusToTime")
	public List<Report> getReportsByTicket(@Param("ticket") Ticket ticket);
	
	
	@Query(value = "SELECT r "
			+ "FROM Report r "
			+ "WHERE (r.statusFromTime BETWEEN :fromDate AND :toDate "
			+ "OR "
			+ "r.statusToTime BETWEEN :fromDate AND :toDate) "
			+ "AND r.executive = :executive "
			+ "ORDER BY r.statusToTime")
	public List<Report> getReportsByExecutiveAndDate(@Param("executive") Employee executive ,@Param("fromDate") Timestamp fromDate, @Param("toDate") Timestamp toDate);
	
	
}
