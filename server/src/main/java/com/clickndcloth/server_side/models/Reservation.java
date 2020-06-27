package com.clickndcloth.server_side.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Reservation")
public class Reservation implements Serializable {
	
	private static final long serialVersionUID = 1559835579977071728L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservation_id;
	private Date date_time;
	private String reference;
	private double total;
	private String status;
	private int quantity;
	
	
	//@JsonIgnore
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	//@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "client_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Client client;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "shop_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Shop shop;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Product product;
	
	public Reservation() {
		super();
	}
	public Reservation(int reservation_id, Date date_time, String reference, double total, String status, int quantity, Product product) {
		super();
		this.reservation_id = reservation_id;
		this.date_time = date_time;
		this.reference = reference;
		this.total = total;
		this.status = status;
		this.quantity = quantity;
		this.product = product;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
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
	public void setClient(Client client) {
		this.client = client;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}