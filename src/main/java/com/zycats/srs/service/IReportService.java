package com.zycats.srs.service;

import com.zycats.srs.entity.Report;

public interface IReportService {

	public Iterable<Report> getAllReports(); 
	
	public void addReport(Report report);
	
	public void removeAllReports();
	
	public void createReports();
	
}
