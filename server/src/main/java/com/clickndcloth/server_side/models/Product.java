package com.clickndcloth.server_side.models;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Product")
public class Product implements Serializable {
	

	private static final long serialVersionUID = -2941819806587004936L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private double price;
	private byte[] image;
	private String discount;
	//private int shop_id_shop;
	//private int shop_admin_id_admin;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "shop_id", nullable = false)
	@JsonIgnore
	private Shop shop;
	
	@ManyToMany
	@JoinTable(name = "Product_categories", joinColumns = @JoinColumn(name = "product_id_product"), 
	  inverseJoinColumns = @JoinColumn(name = "categories_id_categories"))
	@JsonProperty("categories")
	private List<Categories> categories;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id", referencedColumnName = "id", nullable = true)
	private List<Stock> stocks = new ArrayList<Stock>();
	
	public Product() {
		super();
	}
	
	public Product(int id, String name, String description, double price, byte[] image, String discount,
			int shop_admin_id_admin, List<Categories> categories, List<Stock> stocks) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.discount = discount;
		//this.shop_id_shop = shop_id_shop;
		//this.shop_admin_id_admin = shop_admin_id_admin;
		this.categories = categories;
		this.stocks = stocks;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	/*
	 * public int getShop_id_shop() { return shop_id_shop; }
	 * 
	 * public void setShop_id_shop(int shop_id_shop) { this.shop_id_shop =
	 * shop_id_shop; }
	 */
	/*
	 * public int getShop_admin_id_admin() { return shop_admin_id_admin; }
	 * 
	 * public void setShop_admin_id_admin(int shop_admin_id_admin) {
	 * this.shop_admin_id_admin = shop_admin_id_admin; }
	 */

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
