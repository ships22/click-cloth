package com.clickndcloth.server_side.models;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private double price;
	private Blob image;
	private String discount;
	private int shop_id_shop;
	private int shop_admin_id_admin;
	
	@ManyToMany
	@JoinTable(name = "Product_categories", joinColumns = @JoinColumn(name = "product_id_product"), 
	  inverseJoinColumns = @JoinColumn(name = "categories_id_categories"))
	private List<Categories> categories;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id_product", referencedColumnName = "id", nullable = true)
	private List<Stock> productList = new ArrayList<Stock>();
	
	public Product() {
		super();
	}
	
	public Product(int id, String name, String description, double price, Blob image, String discount, int shop_id_shop,
			int shop_admin_id_admin, List<Categories> categories, List<Stock> productList) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.discount = discount;
		this.shop_id_shop = shop_id_shop;
		this.shop_admin_id_admin = shop_admin_id_admin;
		this.categories = categories;
		this.productList = productList;
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

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public int getShop_id_shop() {
		return shop_id_shop;
	}

	public void setShop_id_shop(int shop_id_shop) {
		this.shop_id_shop = shop_id_shop;
	}

	public int getShop_admin_id_admin() {
		return shop_admin_id_admin;
	}

	public void setShop_admin_id_admin(int shop_admin_id_admin) {
		this.shop_admin_id_admin = shop_admin_id_admin;
	}

	public List<Categories> getCategories() {
		return categories;
	}

	public void setCategories(List<Categories> categories) {
		this.categories = categories;
	}

	public List<Stock> getProductList() {
		return productList;
	}

	public void setProductList(List<Stock> productList) {
		this.productList = productList;
	}

}
