package com.zycats.srs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zycats.srs.entity.OSType;
import com.zycats.srs.entity.Status;

@RestController
@RequestMapping(value = "rest/options/*")
public class OptionsController {

	@RequestMapping(value = "get/ostype")
	public OSType[] getOSTypes() {
		return OSType.values();
	}

	@RequestMapping(value = "get/status")
	public Status[] getStatus() {
		return Status.values();
	}
}
