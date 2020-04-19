package com.clickndcloth.server_side.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clickndcloth.server_side.config.JwtUtil;
import com.clickndcloth.server_side.models.PasswordResetToken;
import com.clickndcloth.server_side.models.User;
import com.clickndcloth.server_side.repository.PasswordResetTokenRepository;
import com.clickndcloth.server_side.repository.UserRepository;

@Component
public class UserDomainServiceImpl implements UserDomain{
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private Emailer emailer;
	
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
	public boolean requestPasswordReset(String email) {
		
		boolean result = false;
		User user = userRepository.getByEmail(email);
		
		if(user == null) {
			return result;
		}
		
		String resetPasswordToken = jwtTokenUtil.generateResetPasswordToken(user.getEmail());
		
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		passwordResetToken.setToken(resetPasswordToken);
		passwordResetToken.setUser_id(user.getId());
		passwordResetTokenRepository.save(passwordResetToken);
		
		result = emailer.sendResetLink(user, resetPasswordToken);
		
		return result;
	}

	@Override
	public boolean resetPassword(String token, String password) {
		
		boolean result = false;
		
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
		
		if(passwordResetToken == null) {
			return result;
		} else {
			try {
				if(jwtTokenUtil.isTokenExpired(token)) {
					passwordResetTokenRepository.delete(passwordResetToken);
					return result;
				}
			} catch (Exception e) {
				System.out.println("token expired : " + e.getMessage());
				return result;
			}
			
		}
		
		Optional<User> user = userRepository.findById(passwordResetToken.getUser_id());
		
		if(user == null) {
			return result;
		} user.get().setPassword(password);
		
		User updatedUser = userRepository.saveAndFlush(user.get());
		
		if(updatedUser != null && updatedUser.getPassword().equalsIgnoreCase(password)) {
			result = true;
		}
		passwordResetTokenRepository.delete(passwordResetToken);
		
		return result;
	}
	

}
