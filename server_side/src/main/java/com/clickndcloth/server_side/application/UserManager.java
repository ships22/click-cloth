package com.clickndcloth.server_side.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.dto.UserDto;
import com.clickndcloth.server_side.models.User;
import com.clickndcloth.server_side.services.UserDomainServiceImpl;

@Service
public class UserManager {
	
	@Autowired
	private UserDomainServiceImpl userDomainService;
	
	@Transactional
	public List<UserDto>getAllUser() {
		List<User>userList = userDomainService.getAllUser();
		List<UserDto>userDtos = new ArrayList<UserDto>();
		userList.forEach(user -> {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setEmail(user.getEmail());
			userDto.setRoles(user.getRoles());
			userDto.setIs_active(user.getIs_active());
			userDtos.add(userDto);
		});
		return userDtos;
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public Optional findByEmail(String email) {
		return userDomainService.findByEmail(email);
	}
	
	@Transactional
	public UserDto addUser(User user) {
		User addedUser = userDomainService.addUser(user);
		UserDto userDto = new UserDto();
		userDto.setId(addedUser.getId());
		userDto.setEmail(addedUser.getEmail());
		userDto.setRoles(addedUser.getRoles());
		userDto.setIs_active(addedUser.getIs_active());
		return userDto;
	}
	
	@Transactional
	public UserDto userStatus(int is_active, Integer id) {
		User updatedUser = userDomainService.userStatus(is_active, id);
		UserDto userDto = new UserDto();
		userDto.setId(updatedUser.getId());
		userDto.setEmail(updatedUser.getEmail());
		userDto.setRoles(updatedUser.getRoles());
		userDto.setIs_active(updatedUser.getIs_active());
		return userDto;
	}
	
	@Transactional
	public UserDto updateUserPassword(String password, Integer id) {
		User updatedUser = userDomainService.updateUserPassword(password, id);
		UserDto userDto = new UserDto();
		userDto.setId(updatedUser.getId());
		userDto.setEmail(updatedUser.getEmail());
		userDto.setRoles(updatedUser.getRoles());
		userDto.setIs_active(updatedUser.getIs_active());
		return userDto;
	}
	
}
