package com.zycats.srs.service;

import com.zycats.srs.entity.IssueCategory;

public interface IIssueCategoryService {

	IssueCategory add(IssueCategory issueCategory);

	IssueCategory getById(int id);

	Iterable<IssueCategory> getAll();

	boolean delete(int id);

}