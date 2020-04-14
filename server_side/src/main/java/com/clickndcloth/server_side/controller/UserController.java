package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.clickndcloth.server_side.application.UserManager;
import com.clickndcloth.server_side.dto.UserDto;

@RestController
@RequestMapping(value="/api")
public class UserController {
	
	@Autowired
	private UserManager userManager;

	@GetMapping(value="/users")
	public List<UserDto>getAllUser() {
		return userManager.getAllUser();
	}

}
