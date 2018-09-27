package com.zycats.srs.repository;

import org.springframework.data.repository.CrudRepository;

import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.ManagerDelegate;

public interface ManagerDelegateRepository extends CrudRepository<ManagerDelegate, Employee> {

}
