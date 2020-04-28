package com.clickndcloth.server_side.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private int client_id;
	private int shop_shop_id;
	
	public Reservation() {
		super();
	}
	public Reservation(int reservation_id, Date date_time, String reference, int total, String status, int quantity,
			int client_id, int shop_shop_id) {
		super();
		this.reservation_id = reservation_id;
		this.date_time = date_time;
		this.reference = reference;
		this.total = total;
		this.status = status;
		this.quantity = quantity;
		this.client_id = client_id;
		this.shop_shop_id =shop_shop_id;
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
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getShop_shop_id() {
		return shop_shop_id;
	}
	public void setShop_shop_id(int shop_shop_id) {
		this.shop_shop_id = shop_shop_id;
	}
	
}