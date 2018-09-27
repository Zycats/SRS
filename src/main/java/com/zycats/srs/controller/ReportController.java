package com.zycats.srs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Report;
import com.zycats.srs.service.IReportService;

@RestController
@RequestMapping("rest/report/*")
public class ReportController {
	
	@Autowired
	private IReportService reportService;
	
	@RequestMapping(value ="/create" , method = RequestMethod.GET)
	public void createRepport(){
		
		System.out.println("======================== Creating Reports ======================");
		 reportService.createReports();
		System.out.println("=============Report Creation DONE !!!! =============================");
	}
	
	@RequestMapping(value ="/get/all" , method = RequestMethod.POST, consumes="application/json",produces="application/json")
	public Iterable<Report> getAllReportsByDate(@RequestBody Map<String, String> data){

		 return reportService.getAllReportsByDate(data.get("fromDate"),data.get("toDate"));
		
	}
	
	@RequestMapping(value ="/get/by-ticket-date" , method = RequestMethod.POST, consumes="application/json",produces="application/json")
	public Iterable<Report> getAllReportsByTicketAndDate(@RequestBody Map<String, String> data){

		 return reportService.getReportsByTicketAndDate(Integer.parseInt(data.get("ticket_id")),
				 											data.get("fromDate"),
				 											data.get("toDate"));
		
	}
	
	@RequestMapping(value ="/get/by-executive-date" , method = RequestMethod.POST, consumes="application/json",produces="application/json")
	public Iterable<Report> getAllReportsByExecutiveAndDate(@RequestBody Map<String, String> data){

		 return reportService.getReportsByExecutiveAndDate(data.get("executive_id"),
				 											data.get("fromDate"),
				 											data.get("toDate"));
		
	}
	
}
