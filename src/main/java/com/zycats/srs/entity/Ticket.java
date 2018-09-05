package com.zycats.srs.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_TICKET")
public class Ticket {

	@Id
	@GeneratedValue
	private int id;
	private Employee employee;
	private IssueSubCategory subCategory;
	private Employee engineer;
	private OSType osType;
	private Status status;
	private String description;
	private Timestamp datetime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public IssueSubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(IssueSubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Employee getEngineer() {
		return engineer;
	}

	public void setEngineer(Employee engineer) {
		this.engineer = engineer;
	}

	public OSType getOsType() {
		return osType;
	}

	public void setOsType(OSType osType) {
		this.osType = osType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", employee=" + employee + ", subCategory=" + subCategory + ", engineer=" + engineer
				+ ", osType=" + osType + ", status=" + status + ", description=" + description + ", datetime="
				+ datetime + "]";
	}

}
