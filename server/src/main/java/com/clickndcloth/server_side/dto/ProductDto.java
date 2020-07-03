package com.clickndcloth.server_side.dto;

import java.sql.Blob;
import java.util.List;
import java.util.Set;

import com.clickndcloth.server_side.models.Categories;
import com.clickndcloth.server_side.models.Shop;
import com.clickndcloth.server_side.models.Stock;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ProductDto {
	
	private int id;
	private String name;
	private String description;
	private String productRef;
	private double price;
	private byte[] image;
	private String discount;
	private Set<Categories> categories;
	private List<Stock> stock;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Shop shop;
	
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
	public List<Stock> getStock() {
		return stock;
	}
	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	

}
