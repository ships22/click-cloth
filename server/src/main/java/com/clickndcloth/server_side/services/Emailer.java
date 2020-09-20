package com.clickndcloth.server_side.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.clickndcloth.server_side.models.Admin;
import com.clickndcloth.server_side.models.User;

@Service
public class Emailer {

	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public Emailer (JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public boolean sendResetLink(User user, String token) throws MailException {
		boolean result = true;
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("info@clickandcloth.online");
		mail.setSubject("Password reset link");
		String text = "Cliquez sur le lien pour réinitialiser votre mot de passe. Veuillez noter que le lien n'est valable que pour une heure.";
		mail.setText(text + " Le lien : http://ec2-15-236-232-34.eu-west-3.compute.amazonaws.com:8080/reset_password?token=" + token);
		javaMailSender.send(mail);
		return result;
	}
	
	public boolean sendInitAccountInfo(Admin user, String password) throws MailException {
		boolean result = true;
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		//mail.setFrom("mhsfreelanc.ing@gmail.com");
		mail.setFrom("info@clickandcloth.online");
		mail.setSubject("Account info");
		String text = "Le compte a été créé avec succès. Votre identifant est votre adresse mail et le mot de passe initial est : "
				+ password + " . Veuillez modifier votre mot de passe initial s'il vous plait. Merci";
		mail.setText(text);
		javaMailSender.send(mail);
		return result;
	}
}
