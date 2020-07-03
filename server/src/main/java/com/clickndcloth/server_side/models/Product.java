package com.clickndcloth.server_side.models;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToOne;
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
	private String productRef;
	private double price;
	private byte[] image;
	private String discount;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "shop_id", nullable = false)
	@JsonIgnore
	private Shop shop;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade =  { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE })
	@JoinTable(name = "product_categories", joinColumns = { @JoinColumn(name = "product_id") }, 
	  inverseJoinColumns = {@JoinColumn(name = "categories_id")})
	@JsonProperty("categories")
	private Set<Categories> categories = new HashSet<Categories>();
	
	
//	@JoinColumn(name = "reservation_id")
//	@JsonIgnore
//	@OneToOne(fetch = FetchType.EAGER, mappedBy = "product", cascade=CascadeType.ALL)
//	private Reservation reservation;
	
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	@JsonIgnore
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id", referencedColumnName = "id", nullable = true)
	private List<Stock> stocks = new ArrayList<Stock>();
	
	public Product() {
		super();
	}
	
	public Product(int id, String name, String description, String productRef, double price, byte[] image, String discount,
			int shop_admin_id_admin, Set<Categories> categories, List<Stock> stocks, Shop shop) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.productRef = productRef;
		this.price = price;
		this.image = image;
		this.discount = discount;
		this.categories = categories;
		this.stocks = stocks;
		this.shop = shop;
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

	public String getProductRef() {
		return productRef;
	}

	public void setProductRef(String productRef) {
		this.productRef = productRef;
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


	public Set<Categories> getCategories() {
		return categories;
	}

	public void setCategories(Set<Categories> categories) {
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

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	

}
