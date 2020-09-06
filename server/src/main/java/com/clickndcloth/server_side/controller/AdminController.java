package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 	//@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public List<AdminDto> getAllAdmin() {
		return adminManager.getAllAdmin();
	}
	
	@GetMapping(value = "/admin_by_id/{id}")
	//@PreAuthorize("hasRole('SUPER_ADMIN')")
	public AdminDto getById(@PathVariable ("id") Integer id) {
		return adminManager.findAdminById(id);
	}
	
	@GetMapping(value = "/admin_by_email/{email}")
	//@PreAuthorize("hasRole('SUPER_ADMIN')")
	public AdminDto getByEmail(@PathVariable ("email") String email) {
		return adminManager.findAdminByEmail(email);
	}
	
	@PostMapping(value = "/add_admin", produces = "application/json")
	//@PreAuthorize("hasRole('SUPER_ADMIN')")
	public AdminDto addAdmin(@RequestBody Admin admin) {
		return adminManager.addAdmin(admin);
	}
	
	@PutMapping(value = "/update_admin/{id}")
//	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public AdminDto updateAdmin(@RequestBody Admin admin, @PathVariable("id") int id) {
		return adminManager.updateAdmin(admin);
	}
	
	@DeleteMapping(value = "/delete_admin/{id}")
//	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public String deleteAdmin(@PathVariable ("id") Integer id) {
		return adminManager.deleteAdmin(id);
	}

}
