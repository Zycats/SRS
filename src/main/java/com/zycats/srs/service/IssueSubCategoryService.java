package com.zycats.srs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.IssueSubCategory;
import com.zycats.srs.repository.IssueSubCategoryRepository;

@Service
public class IssueSubCategoryService implements IIssueSubCategoryService {

	@Autowired
	private IssueSubCategoryRepository issueSubCategoryRepository;

	@Override
	public IssueSubCategory add(IssueSubCategory issueSubCategory) {
		return issueSubCategoryRepository.save(issueSubCategory);
	}

	@Override
	public IssueSubCategory getById(int id) {
		return issueSubCategoryRepository.findById(id).get();
	}

	@Override
	public Iterable<IssueSubCategory> getAll() {
		return issueSubCategoryRepository.findAll();
	}

	@Override
	public boolean delete(int id) {
		try {
			issueSubCategoryRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
