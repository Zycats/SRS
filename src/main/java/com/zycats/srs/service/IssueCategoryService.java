package com.zycats.srs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.IssueCategory;
import com.zycats.srs.repository.IssueCategoryRepository;

@Service
public class IssueCategoryService implements IIssueCategoryService {

	@Autowired
	private IssueCategoryRepository issueCategoryRepository;

	@Override
	public IssueCategory add(IssueCategory issueCategory) {
		return issueCategoryRepository.save(issueCategory);
	}

	@Override
	public IssueCategory getById(int id) {
		IssueCategory s = issueCategoryRepository.findById(id).get();
		System.out.println(s.getIssueSubCategories());
		return s;
	}

	@Override
	public Iterable<IssueCategory> getAll() {
		return issueCategoryRepository.findAll();
	}

	@Override
	public boolean delete(int id) {
		try {
			issueCategoryRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
