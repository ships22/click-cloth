package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.clickndcloth.server_side.application.UserManager;
import com.clickndcloth.server_side.config.RequestOperationName;
import com.clickndcloth.server_side.config.RequestOperationStatus;
import com.clickndcloth.server_side.dto.UserDto;
import com.clickndcloth.server_side.models.OperationStatus;
import com.clickndcloth.server_side.models.PasswordReset;
import com.clickndcloth.server_side.models.PasswordResetRequest;

@RestController
@RequestMapping(value="/api")
public class UserController {
	
	@Autowired
	private UserManager userManager;

	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	@GetMapping(value="/users")
	public List<UserDto>getAllUser() {
		return userManager.getAllUser();
	}
	
	@PostMapping("/reset_password")
	public OperationStatus requestReset(@RequestBody PasswordResetRequest passwordResetRequest) {
		
		OperationStatus status = new OperationStatus();
		
		boolean operationResult = userManager.requestPasswordReset(passwordResetRequest.getEmail());
		
		status.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
		status.setOperationResult(RequestOperationStatus.ERROR.name());
		
		if(operationResult) {
			status.setOperationResult(RequestOperationStatus.SUCCESS.name());
		} else {
			status.setOperationResult("USER DOES NOT EXIST");
		}
		return status;
	}
	
	@PostMapping(value ="/update_password", consumes= { MediaType.APPLICATION_JSON_VALUE})
	public OperationStatus resetPassword(@RequestBody PasswordReset passwordReset) {
		OperationStatus status = new OperationStatus();
		
		boolean operationResult = userManager.resetPassword(
				passwordReset.getToken(),
				passwordReset.getPassword()
				);
		
		status.setOperationName(RequestOperationName.PASSWORD_RESET.name());
		status.setOperationResult(RequestOperationStatus.ERROR.name());
		
		if(operationResult) {
			status.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}
		
		return status;
	}

}
