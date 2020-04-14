package com.clickndcloth.server_side.dto;

public class UserDto {
	
	private int id;
	private String email;
	private String password;
	private String roles;
	private int is_active;
	private Integer client_id_client;
	private Integer admin_id_admin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public Integer getClient_id_client() {
		return client_id_client;
	}
	public void setClient_id_client(Integer client_id_client) {
		this.client_id_client = client_id_client;
	}
	public Integer getAdmin_id_admin() {
		return admin_id_admin;
	}
	public void setAdmin_id_admin(Integer admin_id_admin) {
		this.admin_id_admin = admin_id_admin;
	}
	

}
