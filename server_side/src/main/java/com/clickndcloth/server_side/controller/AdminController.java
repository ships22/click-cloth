package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickndcloth.server_side.application.AdminManager;
import com.clickndcloth.server_side.dto.AdminDto;
import com.clickndcloth.server_side.models.Admin;


@RestController
@RequestMapping(value = "/api")
public class AdminController {
	
	@Autowired
	private AdminManager adminManager;

	@GetMapping(value = "/admins")
	public List<AdminDto> getAllAdmin() {
		return adminManager.getAllAdmin();
	}
	
	@GetMapping(value = "/admin_by_id/{id}")
	public AdminDto getById(@PathVariable ("id") Integer id) {
		return adminManager.findAdminById(id);
	}
	
	@PostMapping(value = "/add_admin/{password}", produces = "application/json")
	public AdminDto addClient(@RequestBody Admin admin, @PathVariable ("password") String password) {
		return adminManager.addAdmin(admin, password);
		
	}
	
	@PutMapping(value = "/update_admin")
	public AdminDto updateAdmin(@RequestBody Admin admin) {
		return adminManager.updateAdmin(admin);
	}
	
	@DeleteMapping(value = "/delete_admin/{id}")
	public String deleteAdmin(@PathVariable ("id") Integer id) {
		return adminManager.deleteAdmin(id);
	}

}
