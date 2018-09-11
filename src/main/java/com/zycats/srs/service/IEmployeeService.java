package com.zycats.srs.service;

import org.springframework.security.core.Authentication;

import com.zycats.srs.entity.Employee;

public interface IEmployeeService {

	Employee register(Employee employee);

	Employee getEmployee(String id, String machineIp);

	Iterable<Employee> allEmployees();

	Employee update(Employee employee, Authentication auth);

	boolean deleteEmployee(Authentication auth);

	Employee getEmployeeById(String id);

}