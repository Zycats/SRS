package com.zycats.srs.service;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.zycats.srs.dto.EmployeeLocationDTO;
import com.zycats.srs.dto.Mail;
import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Role;
import com.zycats.srs.event.MailEvent;
import com.zycats.srs.repository.DepartmentRepository;
import com.zycats.srs.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public Employee register(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public EmployeeLocationDTO getEmployeeLocation(String id, String machineIp) {

		EmployeeLocationDTO employeeLocationDTO = new EmployeeLocationDTO();
		Employee employee = getEmployee(id, machineIp);

		System.out.println(
				"===================================STARTED MAIL OPERATION===================================");
		Mail mail = new Mail();
		mail.setSender(employee);
		applicationEventPublisher.publishEvent(new MailEvent(this, mail));
		System.out.println(
				"===================================COMPLETED MAIL OPERATION=================================");

		employeeLocationDTO.setEmployee(employee);
		if (employee.getDepartment() != null)
			employeeLocationDTO
					.setLocation(departmentRepository.getDepartmentLocation(employee.getDepartment().getId()));
		return employeeLocationDTO;
	}

	@Override
	public Employee getEmployee(String id, String machineIp) {
		Employee employee;
		id = getIdFromAuth(id);

		try {
			employee = employeeRepository.findById(id).get();
			employee.setMachineIp(machineIp);
			employeeRepository.save(employee);

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

	public static String getIdFromAuth(String authId) {
		return authId.replaceAll(Pattern.quote("\\"), "\\\\").split("\\\\")[1];
	}

	@Override
	public boolean setRole(Role role, Authentication auth) {
		try {
			Employee employee = getEmployeeById(getIdFromAuth(auth.getName()));
			employee.setRole(role);
			employeeRepository.save(employee);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
