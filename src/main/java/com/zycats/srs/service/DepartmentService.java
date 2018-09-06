package com.zycats.srs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.Department;
import com.zycats.srs.repository.DepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department add(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public Iterable<Department> getAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Department getById(int id) {
		return departmentRepository.findById(id).get();
	}

	@Override
	public boolean delete(int id) {
		try {
			departmentRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
}
