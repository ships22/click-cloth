package com.clickndcloth.server_side.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickndcloth.server_side.application.UserManager;
import com.clickndcloth.server_side.models.OperationStatus;
import com.clickndcloth.server_side.models.PasswordReset;
import com.clickndcloth.server_side.models.PasswordResetRequest;
import com.clickndcloth.server_side.models.User;
import com.clickndcloth.server_side.services.Emailer;

@RestController
public class TestController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private Emailer emailer;
	
	@GetMapping("/")
	public String home() {
		return "Home";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping("/test_mail")
	public String sendMail() {
		User user = new User();
		user.setEmail("sshipsss@gmail.com");
		
		try {
			emailer.sendResetLink(user, "token");
		} catch (MailException e) {
			e.printStackTrace();
		}
		return "Mail sent.";
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
			status.setOperationResult("User not found");
		}
		return status;
	}
	
	@PostMapping(value ="/api/update_password", consumes= { MediaType.APPLICATION_JSON_VALUE})
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
