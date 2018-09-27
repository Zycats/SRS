package com.zycats.srs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "TBL_ISSUE_SUB_CATEGORY")
public class IssueSubCategory {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "category")
	@JsonProperty(access = Access.WRITE_ONLY)
	private IssueCategory issueCategory;

	private IssueType issueType;
	private IssuePriority issuePriority;

	@Column(nullable = true)
	private Boolean requiresApproval;

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

	@JsonIgnore
	public IssueCategory getIssueCategory() {
		return issueCategory;
	}

	@JsonProperty
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

	public Boolean isRequiresApproval() {
		return requiresApproval;
	}

	public void setRequiresApproval(Boolean requiresApproval) {
		this.requiresApproval = requiresApproval;
	}

	@Override
	public String toString() {
		return "IssueSubCategory [id=" + id + ", name=" + name + ", issueCategory=" + issueCategory + ", issueType="
				+ issueType + ", issuePriority=" + issuePriority + ", requiresApproval=" + requiresApproval
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getIssueCategory()=" + getIssueCategory()
				+ ", getIssueType()=" + getIssueType() + ", getIssuePriority()=" + getIssuePriority()
				+ ", isRequiresApproval()=" + isRequiresApproval() + "]";
	}

}
