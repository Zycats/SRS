package com.zycats.srs.service;

import org.springframework.security.core.Authentication;

import com.zycats.srs.dto.EmployeeLocationDTO;
import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Role;

public interface IEmployeeService {

	Employee register(Employee employee);

	Employee getEmployee(String id, String machineIp);

	Iterable<Employee> allEmployees();

	Employee update(Employee employee, Authentication auth);

	boolean deleteEmployee(Authentication auth);

	Employee getEmployeeById(String id);

	EmployeeLocationDTO getEmployeeLocation(String id, String machineIp);

	boolean setRole(Role role, Authentication auth);

	Iterable<Employee> allManagers(String search);

}