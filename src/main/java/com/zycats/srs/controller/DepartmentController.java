package com.zycats.srs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Department;
import com.zycats.srs.service.IDepartmentService;

@RestController
@RequestMapping("/rest/department/*")
public class DepartmentController {

	private IDepartmentService departmentService;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Department addDepartment(@RequestBody Department department) {
		return departmentService.add(department);
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public boolean deleteDepartment(@PathVariable int id) {
		return departmentService.delete(id);
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.POST)
	public Department getDepartmentById(@PathVariable int id) {
		return departmentService.getById(id);
	}

	@RequestMapping(value = "get/all", method = RequestMethod.GET)
	public Iterable<Department> getAllDepartment() {
		return departmentService.getAll();
	}

}
