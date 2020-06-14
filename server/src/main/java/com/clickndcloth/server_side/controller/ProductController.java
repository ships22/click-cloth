package com.clickndcloth.server_side.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clickndcloth.server_side.application.ProductManager;
import com.clickndcloth.server_side.dto.ProductDto;
import com.clickndcloth.server_side.models.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@GetMapping(value = "/productsByShop/{shop_id}")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List <ProductDto> getProductByShopId(@PathVariable ("shop_id") Integer shop_id) {
		return productManager.getProductsByShop(shop_id);
	}
	

	@RequestMapping(value = "/add_product", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ProductDto addProduct(@RequestPart(required=true, value="image") MultipartFile image, @RequestPart(required=true, value="product") String product) throws IOException {
		
		System.out.println("test add product :" + product);
		System.out.println("test add IMG:" + image);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Product newProduct  =	objectMapper.readValue(product, Product.class);
		newProduct.setImage(image.getBytes());
		return productManager.addProduct(newProduct);
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
