package com.clickndcloth.server_side.dto;

public class StockDto {
	
	private int id;
	private int quantite;
	private String size;
	private String colour;
	private int product_id_product;
	private int product_shop_id_shop;
	private int Product_Shop_Admin_idAdmin;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public int getProduct_id_product() {
		return product_id_product;
	}
	public void setProduct_id_product(int product_id_product) {
		this.product_id_product = product_id_product;
	}
	public int getProduct_shop_id_shop() {
		return product_shop_id_shop;
	}
	public void setProduct_shop_id_shop(int product_shop_id_shop) {
		this.product_shop_id_shop = product_shop_id_shop;
	}
	public int getProduct_Shop_Admin_idAdmin() {
		return Product_Shop_Admin_idAdmin;
	}
	public void setProduct_Shop_Admin_idAdmin(int product_Shop_Admin_idAdmin) {
		Product_Shop_Admin_idAdmin = product_Shop_Admin_idAdmin;
	}

}
