package com.clickndcloth.server_side.services;

import java.util.List;
import java.util.Optional;

import com.clickndcloth.server_side.models.Admin;

public interface AdminDomain {
	
	public List<Admin> getAllAdmin();
	
	public Admin addAdmin(Admin admin);
	
	public Admin updateAdmin(Admin admin);
	
	public Optional<Admin> findAdminById(Integer id);
	
	public void deleteAdmin(Integer id);

}
