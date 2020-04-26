package com.clickndcloth.server_side.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.clickndcloth.server_side.models.Shop;

public class AdminDto {
	
	private int id;
	private String first_name;
	private String last_name;
	@Column(unique = true)
	private String email;
	private String address;
	private List<Shop> shops = new ArrayList<Shop>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Shop> getShops() {
		return shops;
	}
	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

}
