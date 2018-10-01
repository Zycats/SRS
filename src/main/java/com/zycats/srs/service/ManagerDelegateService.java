package com.zycats.srs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.ManagerDelegate;
import com.zycats.srs.repository.ManagerDelegateRepository;

@Service
public class ManagerDelegateService implements IManagerDelegateService {

	@Autowired
	private ManagerDelegateRepository managerDelegateRepository;

	@Autowired
	private IEmployeeService employeeService;

	@Override
	public ManagerDelegate add(ManagerDelegate managerDelegate) {
		return managerDelegateRepository.save(managerDelegate);
	}

	@Override
	public ManagerDelegate add(ManagerDelegate managerDelegate, Authentication auth) {
		Employee manager = employeeService.getEmployeeById(EmployeeService.getIdFromAuth(auth.getName()));
		managerDelegate.setManager(manager);
		return managerDelegateRepository.save(managerDelegate);
	}

	@Override
	public Iterable<ManagerDelegate> getAll() {
		return managerDelegateRepository.findAll();
	}

	@Override
	public ManagerDelegate getById(int id) {
		return managerDelegateRepository.findById(id).get();
	}

	@Override
	public boolean delete(int id) {
		managerDelegateRepository.deleteById(id);
		return true;
	}

}
