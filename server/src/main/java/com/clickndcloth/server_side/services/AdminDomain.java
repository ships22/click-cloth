package com.clickndcloth.server_side.services;

import java.util.List;
import java.util.Optional;

import com.clickndcloth.server_side.models.Admin;

public interface AdminDomain {
	
	List<Admin> getAllAdmin();
	
	Admin addAdmin(Admin admin);
	
	Admin updateAdmin(Admin admin);
	
	Optional<Admin> findAdminById(Integer id);
	
	void deleteAdmin(Integer id);
	
	Admin findByEmail(String email);

}
