package com.clickndcloth.server_side.services;



import java.util.List;
import java.util.Optional;

import com.clickndcloth.server_side.models.User;

public interface UserDomain {

	public User addUser(User user);
	
	public Optional<User> findByEmail(String email);
	
	public List<User>getAllUser();
	
	public User userStatus(int is_active, Integer id);
	
	public User updateUserPassword(String password, Integer id);
	
	
	
}
