package com.zycats.srs.service;

import org.springframework.security.core.Authentication;

import com.zycats.srs.entity.ManagerDelegate;

public interface IManagerDelegateService {

	ManagerDelegate add(ManagerDelegate managerDelegate);

	Iterable<ManagerDelegate> getAll();

	ManagerDelegate getById(int id);

	boolean delete(int id);

	ManagerDelegate add(ManagerDelegate managerDelegate, Authentication auth);

}