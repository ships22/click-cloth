package com.clickndcloth.server_side.models;

import javax.persistence.Column;
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
@Table(name = "Product_available")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int quantite;
	private String size;
	@Column(name= "colour", nullable = true)
	private String colour;
	

	/*
	 * @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 * 
	 * @JoinColumn(name = "product_id", nullable = false)
	 * 
	 * @JsonIgnore private Product product;
	 */
	
	public Stock() {
		super();
	}

	public Stock(int id, int quantite, String size, String colour, int product_id_product, int product_shop_id_shop,
			int product_shop_admin_id_admin) {
		super();
		this.id = id;
		this.quantite = quantite;
		this.size = size;
		this.colour = colour;
	}

	/*
	 * public Product getProduct() { return product; }
	 * 
	 * public void setProduct(Product product) { this.product = product; }
	 */

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


	
}
