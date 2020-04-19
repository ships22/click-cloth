package com.clickndcloth.server_side.services.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clickndcloth.server_side.application.UserManager;
import com.clickndcloth.server_side.models.User;
import com.clickndcloth.server_side.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserManager userManager;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userManager.findByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException("User not found with: " + email));
		return user.map(MyUserDetails::new).get();
	}
}
