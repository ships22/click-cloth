package com.clickndcloth.server_side.services;

import java.util.List;
import java.util.Optional;

import com.clickndcloth.server_side.models.Product;

public interface ProductDomain {
	
	Product addProduct(Product product);
	
	List<Product> getAllProduct();
	
	List<Product>getAllProductLadies();
	
	List<Product>getAllProductGents();
	
	List<Product>getAllProductChildren();
	
	List<Product>getAllByProductRef(String ref);
	
	List<Product>getAllProductWithDiscount();
	
	List<Product> getProductsByShopId(int shop_id);
	
	List<Product>getAllByIds(List<Integer> ids);
	
	Product getProductById(int id);
	
	Product updateProduct(Product product);
	
	String deleteProduct(Integer id);
	
	Optional<Product> findById(int id);

}
