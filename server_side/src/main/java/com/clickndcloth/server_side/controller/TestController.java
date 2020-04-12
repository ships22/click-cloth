package com.clickndcloth.server_side.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


	@GetMapping("/")
	public String home() {
		return "Home";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String admin() {
		return "admin";
	}
}
