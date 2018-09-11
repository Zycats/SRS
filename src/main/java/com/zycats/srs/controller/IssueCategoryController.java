package com.zycats.srs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.IssueCategory;
import com.zycats.srs.service.IIssueCategoryService;

@RestController
@RequestMapping("/rest/issue-category/*")
public class IssueCategoryController {

	@Autowired
	private IIssueCategoryService issueCategoryService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public IssueCategory add(@RequestBody IssueCategory issueCategory) {
		return issueCategoryService.add(issueCategory);
	}

	@RequestMapping(value = "get/all", method = RequestMethod.GET)
	public Iterable<IssueCategory> getAll() {
		return issueCategoryService.getAll();
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.POST)
	public IssueCategory getById(@PathVariable int id) {
		return issueCategoryService.getById(id);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public boolean delete(@PathVariable int id) {
		return issueCategoryService.delete(id);
	}

}
