package com.clickndcloth.server_side.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clickndcloth.server_side.models.Admin;
import com.clickndcloth.server_side.repository.AdminRepository;

@Component
public class AdminDomainServiceImpl implements AdminDomain {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}

	@Override
	public Admin addAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		return adminRepository.saveAndFlush(admin);
	}

	@Override
	public Optional<Admin> findAdminById(Integer id) {
		return adminRepository.findById(id);
	}

	@Override
	public void deleteAdmin(Integer id) {
		adminRepository.deleteById(id);
	}

	@Override
	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}
	

}
