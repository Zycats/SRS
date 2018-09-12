package com.zycats.srs.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.zycats.srs.entity.Department;
import com.zycats.srs.entity.Location;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
	@Query("SELECT L FROM Department AS D INNER JOIN Location AS L ON D.location.id = L.id WHERE D.id = :id")
	Location getDepartmentLocation(@Param("id") int id);
}
