package com.clickndcloth.server_side.dto;

import java.sql.Blob;
import java.util.List;

import com.clickndcloth.server_side.models.Categories;
import com.clickndcloth.server_side.models.Stock;

public class ProductDto {
	
	private int id;
	private String name;
	private String description;
	private double price;
	private byte[] image;
	private String discount;
	private int shop_id_shop;
	private int shop_admin_id_admin;
	private List<Categories> categories;
	private List<Stock> stock;
	
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
	public List<Stock> getStock() {
		return stock;
	}
	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}
	

}
