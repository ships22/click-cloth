package com.clickndcloth.server_side.config;

import org.springframework.stereotype.Component;

@Component
public class Constant {
	
	public int resetPasswordTime = 1000 * 60;
	public int loginTokenTime = 1000 * 60 * 60;

}
