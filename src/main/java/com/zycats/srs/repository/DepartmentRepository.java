package com.zycats.srs.repository;

import org.springframework.data.repository.CrudRepository;

import com.zycats.srs.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
