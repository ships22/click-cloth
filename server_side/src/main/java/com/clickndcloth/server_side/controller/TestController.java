package com.clickndcloth.server_side.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickndcloth.server_side.models.User;
import com.clickndcloth.server_side.services.Emailer;

@RestController
@RequestMapping(value = "/api")
public class TestController {


	@Autowired
	private Emailer emailer;
	
	@GetMapping("/")
	public String home() {
		return "Home";
	}
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping("/test_mail")
	public String sendMail() {
		User user = new User();
		user.setEmail("sshipsss@gmail.com");
		
		try {
			emailer.sendResetLink(user, "token");
		} catch (MailException e) {
			e.printStackTrace();
		}
		return "Mail sent.";
	}
	
	
}
