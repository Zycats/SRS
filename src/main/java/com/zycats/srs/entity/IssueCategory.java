package com.zycats.srs.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ISSUE_CATEGORY")
public class IssueCategory {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	@OneToMany(mappedBy = "issueCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<IssueSubCategory> issueSubCategories;

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

	public Set<IssueSubCategory> getIssueSubCategories() {
		return issueSubCategories;
	}

	public void setIssueSubCategories(Set<IssueSubCategory> issueSubCategories) {
		this.issueSubCategories = issueSubCategories;
	}

	@Override
	public String toString() {
		return "IssueCategory [id=" + id + ", name=" + name + "]";
	}

}
