package com.zycats.srs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.IssueSubCategory;
import com.zycats.srs.service.IIssueSubCategoryService;

@RestController
@RequestMapping("rest/issue-sub-category/*")
public class IssueSubCategoryController {

	@Autowired
	private IIssueSubCategoryService issueSubCategoryService;

	@RequestMapping("add")
	public IssueSubCategory add(@RequestBody IssueSubCategory issueSubCategory) {
		return issueSubCategoryService.add(issueSubCategory);
	}

	@RequestMapping("get/all")
	public Iterable<IssueSubCategory> getAll() {
		return issueSubCategoryService.getAll();
	}

	@RequestMapping("get/{id}")
	public IssueSubCategory getById(@PathVariable int id) {
		return issueSubCategoryService.getById(id);
	}

	@RequestMapping("get/all")
	public boolean delete(@PathVariable int id) {
		return issueSubCategoryService.delete(id);
	}

}
