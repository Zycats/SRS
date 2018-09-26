package com.zycats.srs.entity;

import java.util.Arrays;

public enum Role {
	ADMIN("ADMIN"), EXECUTIVE("EXECUTIVE"), EMPLOYEE("EMPLOYEE");

	private String value;

	private Role(String value) {
		this.value = value;
	}

	public static Role fromValue(String value) {
		for (Role category : values()) {
			if (category.value.equalsIgnoreCase(value)) {
				return category;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}
}
