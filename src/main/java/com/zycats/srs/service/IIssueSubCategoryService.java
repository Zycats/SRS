package com.zycats.srs.service;

import com.zycats.srs.entity.IssueSubCategory;

public interface IIssueSubCategoryService {

	IssueSubCategory add(IssueSubCategory issueSubCategory);

	IssueSubCategory getById(int id);

	Iterable<IssueSubCategory> getAll();

	boolean delete(int id);

}