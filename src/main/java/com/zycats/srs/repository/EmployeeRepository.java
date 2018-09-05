package com.zycats.srs.repository;

import org.springframework.data.repository.CrudRepository;

import com.zycats.srs.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
