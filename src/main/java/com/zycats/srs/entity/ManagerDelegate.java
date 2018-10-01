package com.zycats.srs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_MANAGER_DELEGATE")
public class ManagerDelegate {

	@Id
	@GeneratedValue
	private int id;

	@OneToOne
	@JoinColumn(name = "manager_id")
	private Employee manager;

	@ManyToOne
	@JoinColumn(name = "delegate_manager_id")
	private Employee delegate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Employee getDelegate() {
		return delegate;
	}

	public void setDelegate(Employee delegate) {
		this.delegate = delegate;
	}

	@Override
	public String toString() {
		return "ManagerDelegate [id=" + id + ", manager=" + manager + ", delegate=" + delegate + "]";
	}

}
