package com.zycats.srs.service;

import com.zycats.srs.entity.Department;

public interface IDepartmentService {

	Department add(Department department);

	Iterable<Department> getAll();

	Department getById(int id);

	boolean delete(int id);

}