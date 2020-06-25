package com.clickndcloth.server_side.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Shop")
public class Shop implements Serializable {
	
	private static final long serialVersionUID = 8452543819399075149L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shop_id;
	private String name;
	private String address;
	private String email;
	private String phone;
	private int is_active;
	//@Column(name="admin_id")
	//private int adminId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "admin_id", nullable = false)
	@JsonIgnore
	private Admin admin;
	
	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Product> productList = new ArrayList<Product>();
	
//	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	/*
	 * @OneToMany(cascade=CascadeType.ALL)
	 * 
	 * @JoinColumn(name="shop_id_shop", referencedColumnName = "shop_id", nullable =
	 * true) private List<Product> productList = new ArrayList<Product>();
	 */
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="shop_id", referencedColumnName = "shop_id", nullable = true)
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	public Shop() {
		super();
	}

	public Shop(int shop_id, String name, String address, String email, String phone, int is_active,
			List<Product> productList, List<Reservation> reservations) {
		super();
		this.shop_id = shop_id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.is_active = is_active;
		//this.adminId = adminId;
		this.productList = productList;
		this.reservations = reservations;
	}

	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	/*
	 * public int getAdmin_id() { return adminId; } public void setAdmin_id(int
	 * adminId) { this.adminId = adminId; }
	 */
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

}
