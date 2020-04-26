package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickndcloth.server_side.application.ProductManager;
import com.clickndcloth.server_side.dto.ProductDto;
import com.clickndcloth.server_side.models.Product;

@RestController
@RequestMapping(value = "/api")
public class ProductController {
	
	@Autowired
	private ProductManager productManager;
	
	@GetMapping(value = "/products")
	@PreAuthorize("permitAll()")
	public List<ProductDto> getAllProduct() {
		return productManager.getAllProduct();
	}
	

	@GetMapping(value = "/product/{product_id}")
	@PreAuthorize("permitAll()")
	public ProductDto getProductById(@PathVariable ("product_id") Integer product_id) {
		return productManager.getProductById(product_id);
	}
	
	
	@PostMapping(value = "/add_product", produces = "application/json")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ProductDto addProduct(@RequestBody Product product) {
		return productManager.addProduct(product);
		
	}
	
	@PutMapping(value = "/update_product")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ProductDto updateProduct(@RequestBody Product product) {
		return productManager.updateProduct(product);
	}
	
	@DeleteMapping(value = "/delete_product/{product_id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteCategory(@PathVariable ("product_id") Integer product_id) {
		return productManager.deleteProduct(product_id);
	}

}
