package com.clickndcloth.server_side.services;

import java.util.List;

import com.clickndcloth.server_side.models.Product;

public interface ProductDomain {
	
	Product addProduct(Product product);
	
	List<Product> getAllProduct();
	
	List<Product> getProductsByShopId(int shop_id);
	
	Product getProductById(int id);
	
	Product updateProduct(Product product);
	
	String deleteProduct(Integer id);
	
	

}
