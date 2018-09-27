package com.zycats.srs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
}
