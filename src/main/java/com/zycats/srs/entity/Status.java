package com.zycats.srs.entity;

public enum Status {
	OPEN, CLOSED, ONHOLD, UNRESOLVABLE, WORKING, PENDING_APPROVAL("PENDING APPROVAL");

	private String status;

	Status(String status) {
		this.status = status;
	}

	Status() {

	}
}
