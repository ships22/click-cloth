package com.clickndcloth.server_side.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Shop")
public class Shop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private String email;
	private String phone;
	private int is_active;
	private int admin_id_admin;
	
	@ManyToOne
	private Admin admin;
	
	public Shop() {
		super();
	}
	public Shop(int id, String name, String address, String email, String phone, int is_active, int admin_id_admin) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.is_active = is_active;
		this.admin_id_admin = admin_id_admin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public int getAdmin_id_admin() {
		return admin_id_admin;
	}
	public void setAdmin_id_admin(int admin_id_admin) {
		this.admin_id_admin = admin_id_admin;
	}
	

}