package com.clickndcloth.server_side.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clickndcloth.server_side.models.Product;
import com.clickndcloth.server_side.repository.ProductRepository;

@Component
public class ProductDomainServiceImpl implements ProductDomain{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}
	
	@Override
	public List<Product> getProductsByShopId(Integer shop_id) {
		return productRepository.getProductsByShopId(shop_id);
	}

	@Override
	public Product getProductById(Integer id) {
		return productRepository.getOne(id);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.saveAndFlush(product);
	}

	@Override
	public String deleteProduct(Integer id) {
		productRepository.deleteById(id);
		return "Product with id : " + id + " has been deleted successfully";
	}

	

}
