package com.zycats.srs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.IssueCategory;
import com.zycats.srs.service.IIssueCategoryService;

@RestController
@RequestMapping("rest/issue-category/*")
public class IssueCategoryController {

	@Autowired
	private IIssueCategoryService issueCategoryService;

	@RequestMapping("add")
	public IssueCategory add(@RequestBody IssueCategory issueCategory) {
		return issueCategoryService.add(issueCategory);
	}

	@RequestMapping("get/all")
	public Iterable<IssueCategory> getAll() {
		return issueCategoryService.getAll();
	}

	@RequestMapping("get/{id}")
	public IssueCategory getById(@PathVariable int id) {
		return issueCategoryService.getById(id);
	}

	@RequestMapping("get/all")
	public boolean delete(@PathVariable int id) {
		return issueCategoryService.delete(id);
	}

}
