package com.zycats.srs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	@RequestMapping("/")
	public String auth() {
		return "home-new2";
	}
}
