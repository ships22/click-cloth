package com.clickndcloth.server_side.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservation_id;
	private Date date_time;
	private String reference;
	private int total;
	private String status;
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "client_id", nullable = false)
	@JsonIgnore
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "shop_id", nullable = false)
	@JsonIgnore
	private Shop shop;
	
	@ManyToMany(mappedBy = "reservation")
	private List<Product>products;
	
	public Reservation() {
		super();
	}
	public Reservation(int reservation_id, Date date_time, String reference, int total, String status, int quantity, List<Product>products) {
		super();
		this.reservation_id = reservation_id;
		this.date_time = date_time;
		this.reference = reference;
		this.total = total;
		this.status = status;
		this.quantity = quantity;
		this.products = products;
	}
	public int getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Client getClient() {
		return client;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
}