package com.zycats.srs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ISSUE_SUB_CATEGORY")
public class IssueSubCategory {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "category")
	private IssueCategory issueCategory;

	private IssueType issueType;
	private IssuePriority issuePriority;

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

	public IssueCategory getIssueCategory() {
		return issueCategory;
	}

	public void setIssueCategory(IssueCategory issueCategory) {
		this.issueCategory = issueCategory;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public IssuePriority getIssuePriority() {
		return issuePriority;
	}

	public void setIssuePriority(IssuePriority issuePriority) {
		this.issuePriority = issuePriority;
	}

	@Override
	public String toString() {
		return "IssueSubCategory [id=" + id + ", name=" + name + ", issueCategory=" + issueCategory + ", issueType="
				+ issueType + ", issuePriority=" + issuePriority + "]";
	}

}
