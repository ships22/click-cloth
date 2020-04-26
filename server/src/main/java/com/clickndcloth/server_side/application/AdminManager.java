package com.clickndcloth.server_side.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.config.SecurityConfig;
import com.clickndcloth.server_side.dto.AdminDto;
import com.clickndcloth.server_side.models.Admin;
import com.clickndcloth.server_side.models.User;
import com.clickndcloth.server_side.services.AdminDomainServiceImpl;
import com.clickndcloth.server_side.services.Emailer;
import com.clickndcloth.server_side.services.UserDomainServiceImpl;

@Service
public class AdminManager {
	
	@Autowired
	private AdminDomainServiceImpl adminDomainService;
	
	@Autowired
	private UserDomainServiceImpl userDomainService;
	
	@Autowired
	private SecurityConfig securityConfig;
	
	@Autowired
	private Emailer emailer;
	
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
			adminDto.setShop(admin.getShopList());
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
		adminDto.setShop(admin.get().getShopList());
		return adminDto;
	}
	
	
	@Transactional
	public AdminDto addAdmin(Admin admin) {
		if(userDomainService.getByEmail(admin.getEmail()) ==  null) {
			Admin addedAdmin = adminDomainService.addAdmin(admin);
			String inititalPassword = randomPasswordGenerator();
			String encodedPassword = securityConfig.passwordEncoder().encode(inititalPassword);

			User newUser = new User();
			newUser.setEmail(addedAdmin.getEmail());
			newUser.setPassword(encodedPassword);
			newUser.setRoles("ROLE_ADMIN");
			newUser.setIs_active(1);
			newUser.setAdmin_id_admin(addedAdmin.getId());
			userDomainService.addUser(newUser);
			
			if(addedAdmin != null) {
				emailer.sendInitAccountInfo(addedAdmin, inititalPassword);
				AdminDto adminDto = new AdminDto();
				adminDto.setId(addedAdmin.getId());
				adminDto.setFirst_name(addedAdmin.getFirst_name());
				adminDto.setLast_name(addedAdmin.getLast_name());
				adminDto.setEmail(addedAdmin.getEmail());
				adminDto.setAddress(addedAdmin.getAddress());
				return adminDto;
			}

		} System.out.println("User exists with mail id : " + admin.getEmail());
				
		return null;

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
	
	private String randomPasswordGenerator() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	 
	    String generatedPassword = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    return generatedPassword;
	}
	
 

}
