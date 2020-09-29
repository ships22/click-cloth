package com.clickndcloth.server_side;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ServerSideApplication {
	
	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**") .allowedMethods("GET", "POST", "PUT", "DELETE")
	 * .allowedHeaders("*") .allowedOrigins("http://localhost:4200", "*",
	 * "http://localhost:8100").allowCredentials(true); } };
	 * 
	 * };
	 */
	 
	
	public static void main(String[] args) {
		SpringApplication.run(ServerSideApplication.class, args);
	}

}
