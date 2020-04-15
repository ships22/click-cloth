package com.clickndcloth.server_side.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.config.SecurityConfig;
import com.clickndcloth.server_side.dto.AdminDto;
import com.clickndcloth.server_side.models.Admin;
import com.clickndcloth.server_side.models.User;
import com.clickndcloth.server_side.services.AdminDomainServiceImpl;
import com.clickndcloth.server_side.services.UserDomainServiceImpl;

@Service
public class AdminManager {
	
	@Autowired
	private AdminDomainServiceImpl adminDomainService;
	
	@Autowired
	private UserDomainServiceImpl userDomainService;
	
	@Autowired
	private SecurityConfig securityConfig;
	
	@Transactional
	public List<AdminDto>getAllAdmin() {
		List<Admin>adminList = adminDomainService.getAllAdmin();
		List<AdminDto>adminDtos = new ArrayList<AdminDto>();
		adminList.forEach(admin -> {
			AdminDto adminDto = new AdminDto();
			adminDto.setId(admin.getId());
			adminDto.setFirst_name(admin.getFirst_name());
			adminDto.setLast_name(admin.getLast_name());
			adminDto.setEmail(admin.getEmail());
			adminDto.setAddress(admin.getAddress());
			adminDtos.add(adminDto);
		});
		return adminDtos;
	}
	
	@Transactional
	public AdminDto findAdminById(Integer id) {
		Optional<Admin> admin = adminDomainService.findAdminById(id);
		AdminDto adminDto = new AdminDto();
		adminDto.setId(admin.get().getId());
		adminDto.setFirst_name(admin.get().getFirst_name());
		adminDto.setLast_name(admin.get().getLast_name());
		adminDto.setEmail(admin.get().getEmail());
		adminDto.setAddress(admin.get().getAddress());
		return adminDto;
	}
	
	@Transactional
	public AdminDto addAdmin(Admin admin, String password) {
		Admin addedAdmin = adminDomainService.addAdmin(admin);
		
		String encodedPassword = securityConfig.passwordEncoder().encode(password);
		System.out.println("test encryption : " + encodedPassword);
		
		User newUser = new User();
		newUser.setEmail(addedAdmin.getEmail());
		newUser.setPassword(encodedPassword);
		newUser.setRoles("ROLE_ADMIN");
		newUser.setIs_active(1);
		newUser.setAdmin_id_admin(addedAdmin.getId());
		userDomainService.addUser(newUser);
		
		AdminDto adminDto = new AdminDto();
		adminDto.setId(addedAdmin.getId());
		adminDto.setFirst_name(addedAdmin.getFirst_name());
		adminDto.setLast_name(addedAdmin.getLast_name());
		adminDto.setEmail(addedAdmin.getEmail());
		adminDto.setAddress(addedAdmin.getAddress());
		return adminDto;

	}
	
	@Transactional
	public AdminDto updateAdmin(Admin admin) {
		Admin updatedAdmin = adminDomainService.updateAdmin(admin);
		AdminDto adminDto = new AdminDto();
		adminDto.setId(updatedAdmin.getId());
		adminDto.setFirst_name(updatedAdmin.getFirst_name());
		adminDto.setLast_name(updatedAdmin.getLast_name());
		adminDto.setEmail(updatedAdmin.getEmail());
		adminDto.setAddress(updatedAdmin.getAddress());
		return adminDto;
	}
	
	@Transactional
	public String deleteAdmin(Integer id) {
		adminDomainService.deleteAdmin(id);
		return "Admin with id : " + id + " has been deleted succesfully";
	}

}
