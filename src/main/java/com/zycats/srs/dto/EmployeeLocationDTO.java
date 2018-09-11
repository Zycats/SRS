package com.zycats.srs.dto;

import com.zycats.srs.entity.Employee;
import com.zycats.srs.entity.Location;

public class EmployeeLocationDTO {

	private Employee employee;
	private Location location;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
