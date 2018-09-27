package com.zycats.srs.property;

import java.beans.PropertyEditorSupport;

import com.zycats.srs.entity.Role;

public class RoleConverter extends PropertyEditorSupport {

	public void setAsText(final String text) throws IllegalArgumentException {
		setValue(Role.fromValue(text));
	}

}
