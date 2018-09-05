package com.zycats.srs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_FOLDER")
public class Folder {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Employee owner;

	private String path;

	@ManyToOne
	@JoinColumn(name = "sub_category")
	private IssueSubCategory subCategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public IssueSubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(IssueSubCategory subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "Folder [id=" + id + ", name=" + name + ", owner=" + owner + ", path=" + path + ", subCategory="
				+ subCategory + "]";
	}

}
