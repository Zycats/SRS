package com.zycats.srs.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.ocpsoft.prettytime.PrettyTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "TBL_TICKET")
public class Ticket {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private IssueSubCategory subCategory;

	@ManyToOne
	@JoinColumn(name = "engineer_id")
	private Employee engineer;

	private OSType osType;
	private Status status;
	private String description;
	private Timestamp datetime;

	@JsonInclude
	@Transient
	private String timeAgo;

	public String getTimeAgo() {
		return timeAgo;
	}

	public void setTimeAgo(String timeAgo) {
		this.timeAgo = timeAgo;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
	private Set<Comment> comments;

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
		this.setTimeAgo(new PrettyTime().format(this.getDatetime()));
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", employee=" + employee + ", subCategory=" + subCategory + ", engineer=" + engineer
				+ ", osType=" + osType + ", status=" + status + ", description=" + description + ", datetime="
				+ datetime + "]";
	}

}
