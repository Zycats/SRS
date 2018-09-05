package com.zycats.srs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_EMPLOYEE")
public class Employee {

	@Id
	private String id;
	private Role role;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	private String email;
	private String seatNo;
	private int extNo;
	private String machineIp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public int getExtNo() {
		return extNo;
	}

	public void setExtNo(int extNo) {
		this.extNo = extNo;
	}

	public String getMachineIp() {
		return machineIp;
	}

	public void setMachineIp(String machineIp) {
		this.machineIp = machineIp;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", role=" + role + ", department=" + department + ", email=" + email + ", seatNo="
				+ seatNo + ", extNo=" + extNo + ", machineIp=" + machineIp + "]";
	}

}
