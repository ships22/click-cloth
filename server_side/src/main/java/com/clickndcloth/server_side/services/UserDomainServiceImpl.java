package com.clickndcloth.server_side.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clickndcloth.server_side.models.User;
import com.clickndcloth.server_side.repository.UserRepository;

@Component
public class UserDomainServiceImpl implements UserDomain{
	

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public List<User>getAllUser() {
		return userRepository.findAll();
	}
	
	@Override
	public User userStatus(int is_active, Integer id) {
		return userRepository.userStatus(is_active, id);
	}
	
	@Override
	public User updateUserPassword(String password, Integer id) {
		return userRepository.updateUserPassword(password, id);
	}
	

}
