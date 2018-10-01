package com.zycats.srs.service;

import java.sql.Timestamp;

import com.zycats.srs.entity.Report;

public interface IReportService {
	
	public void addReport(Report report);
	
	public void removeAllReports();
	
	public void createReports();
	
	public Iterable<Report> getAllReportsByDate(Timestamp fromDate, Timestamp toDate); 
	
	public Iterable<Report> getReportsByTicketAndDate(int ticket_id,String fromDate,String toDate );
	
	public Iterable<Report> getReportsByExecutiveAndDate(String executive_id,String fromDate,String toDate );

	Iterable<Report> getReportsByTicket(int ticket_id);
	
	
	
}
