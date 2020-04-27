package com.clickndcloth.server_side.services;

import java.util.List;

import com.clickndcloth.server_side.models.Product;

public interface ProductDomain {
	
	Product addProduct(Product product);
	
	List<Product> getAllProduct();
	
	List<Product> getProductsByShopId(Integer shop_id);
	
	Product getProductById(Integer id);
	
	Product updateProduct(Product product);
	
	String deleteProduct(Integer id);
	
	

}
