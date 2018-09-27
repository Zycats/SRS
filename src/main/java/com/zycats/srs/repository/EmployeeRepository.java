package com.zycats.srs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zycats.srs.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

	@Query("SELECT  e FROM Employee e WHERE e.role = com.zycats.srs.entity.Role.MANAGER AND e.id LIKE %:search%")
	public List<Employee> getManagersByName(@Param("search") String search);
}
