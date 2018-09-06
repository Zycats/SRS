package com.zycats.srs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.Department;
import com.zycats.srs.service.IDepartmentService;

@RestController
@RequestMapping("rest/department/*")
public class DepartmentController {

	private IDepartmentService departmentService;

	@RequestMapping(name = "add")
	public Department addDepartment(@RequestBody Department department) {
		return departmentService.add(department);
	}

	@RequestMapping(name = "delete/{id}")
	public boolean addDepartment(@PathVariable int id) {
		return departmentService.delete(id);
	}

	@RequestMapping(name = "get/{id}")
	public Department getDepartmentById(@PathVariable int id) {
		return departmentService.getById(id);
	}

	@RequestMapping(name = "get/all")
	public Iterable<Department> getAllDepartment() {
		return departmentService.getAll();
	}

}
