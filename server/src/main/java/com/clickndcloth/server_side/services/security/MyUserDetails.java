package com.clickndcloth.server_side.services.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.clickndcloth.server_side.models.User;



@SuppressWarnings("serial")
public class MyUserDetails implements UserDetails {

	private String userName;
	private String password;
	private int isActive;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails(User user) {
		this.userName = user.getEmail();
		this.password = user.getPassword();
		this.isActive = user.getIs_active();
		this.authorities = Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		if(isActive == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
	

}
