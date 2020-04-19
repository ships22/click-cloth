package com.clickndcloth.server_side.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResetPassController {
	
	@RequestMapping("/reset_password")
	public String hello() {
		return "hello.html";
	}

}
