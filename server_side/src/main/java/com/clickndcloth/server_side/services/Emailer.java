package com.clickndcloth.server_side.services;

import javax.swing.text.html.parser.ContentModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.clickndcloth.server_side.models.User;

@Service
public class Emailer {

	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public Emailer (JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public boolean sendResetLink(User user, String token) throws MailException{
		boolean result = true;
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("mhsfreelanc.ing@gmail.com");
		mail.setSubject("Password reset link");
		mail.setText("http://localhost:8080/reset_password?token=" + token);
		
		javaMailSender.send(mail);
		System.out.println("here in the mail");
		return result;
	}
}
