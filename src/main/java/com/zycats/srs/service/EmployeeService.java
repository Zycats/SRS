package com.zycats.srs.service;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Role;
import com.zycats.srs.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee register(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployee(String id, String machineIp) {
		Employee employee;
		id = getIdFromAuth(id);

		try {
			employee = employeeRepository.findById(id).get();

		} catch (NoSuchElementException e) {
			employee = new Employee();
			employee.setEmail(id + "@zycus.com");
			employee.setId(id);
			employee.setRole(Role.EMPLOYEE);
			employee.setFirstLogin(true);
			employee.setMachineIp(machineIp);
			employee = register(employee);
		}
		return employee;
	}

	@Override
	public Iterable<Employee> allEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee update(Employee employee, Authentication auth) {
		Employee emp;

		String id = getIdFromAuth(auth.getName());
		employee.setId(id);
		employee.setEmail(id + "@zycus.com");

		try {
			emp = employeeRepository.findById(id).get();

			// If found then persist the role
			employee.setRole(emp.getRole());
		} catch (NoSuchElementException e) {

			// If employee not found then set ROLE = EMPLOYEE as DEFAULT
			employee.setRole(Role.EMPLOYEE);
		}

		return employeeRepository.save(employee);
	}

	@Override
	public boolean deleteEmployee(Authentication auth) {
		try {
			employeeRepository.deleteById(getIdFromAuth(auth.getName()));
		} catch (IllegalArgumentException e) {
			return false;
		}
		return true;
	}
	
	
	@Override
	public Employee getEmployeeById(String id) {
		Employee employee;
		try {
			employee = employeeRepository.findById(id).get();
		} catch (IllegalArgumentException e) {
			return null;
		}
		return employee;
	}
	
	

	private static String getIdFromAuth(String authId) {
		return authId.replaceAll(Pattern.quote("\\"), "\\\\").split("\\\\")[1];
	}

}
