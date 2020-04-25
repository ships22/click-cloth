package com.clickndcloth.server_side.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String email;
	private String password;
	private String roles;
	private int is_active;
	private Integer client_id_client;
	private Integer admin_id_admin;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", unique = true, nullable = true)
	private Client client;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", unique = true, nullable = true)
	private Admin admin;
	

	public User() {
		super();
	}
	
	public User(int id, String email, String password, String roles, int is_active, 
				Integer client_id_client, Integer admin_id_admin, Client client, Admin admin) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.is_active = is_active;
		this.client_id_client = client_id_client;
		this.admin_id_admin = admin_id_admin;
		this.client = client;
		this.admin = admin;
	}

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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
}
