package com.clickndcloth.server_side.services;



import java.util.List;
import java.util.Optional;

import com.clickndcloth.server_side.models.User;

public interface UserDomain {

	User addUser(User user);
	
	Optional<User> findByEmail(String email);
	
	List<User>getAllUser();
	
	User userStatus(int is_active, Integer id);
	
	User updateUserPassword(String password, Integer id);
	
	boolean requestPasswordReset(String email);
	
	boolean resetPassword(String token, String password);
	
}
