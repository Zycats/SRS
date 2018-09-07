package com.zycats.srs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.IssueSubCategory;
import com.zycats.srs.service.IIssueSubCategoryService;

@RestController
@RequestMapping("/rest/issue-sub-category/*")
public class IssueSubCategoryController {

	@Autowired
	private IIssueSubCategoryService issueSubCategoryService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public IssueSubCategory add(@RequestBody IssueSubCategory issueSubCategory) {
		return issueSubCategoryService.add(issueSubCategory);
	}

	@RequestMapping(value = "get/all", method = RequestMethod.GET)
	public Iterable<IssueSubCategory> getAll() {
		return issueSubCategoryService.getAll();
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.POST)
	public IssueSubCategory getById(@PathVariable int id) {
		return issueSubCategoryService.getById(id);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public boolean delete(@PathVariable int id) {
		return issueSubCategoryService.delete(id);
	}

}
