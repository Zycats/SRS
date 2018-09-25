package com.zycats.srs.exception;

import com.zycats.srs.entity.Employee;

public class InsufficientPriviledgesException extends Exception {

	private Employee employee;

	public InsufficientPriviledgesException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsufficientPriviledgesException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InsufficientPriviledgesException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InsufficientPriviledgesException(Employee employee, String message) {
		super(message);
		this.employee = employee;
	}

	public InsufficientPriviledgesException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public Employee getEmployee() {
		return employee;
	}

}
