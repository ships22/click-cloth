package com.clickndcloth.server_side.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.clickndcloth.server_side.application.StockManager;
import com.clickndcloth.server_side.dto.ProductDto;
import com.clickndcloth.server_side.models.Categories;
import com.clickndcloth.server_side.models.Product;
import com.clickndcloth.server_side.models.Stock;
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
	
	@Autowired
	private StockManager stockManager;
	
	
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
	public List <ProductDto> getProductByShopId(@PathVariable ("shop_id") int shop_id) {
		return productManager.getProductsByShop(shop_id);
	}
	

	@RequestMapping(value = "/add_product/{shop_id}/cat/{cat_id}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ProductDto addProduct(@RequestPart(required=true, value="image") MultipartFile image, @RequestPart(required=true, value="product") String product, 
			@RequestPart(required=true, value="stock") String stock, @PathVariable(name="shop_id") Integer shop_id,
			@PathVariable(name="cat_id") Integer cat_id) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Product newProduct  =	objectMapper.readValue(product, Product.class);
		Stock newStock =  objectMapper.readValue(stock, Stock.class);
		//System.out.println("test stock : " + newStock.getProduct_Shop_Admin_idAdmin() + newStock.getProduct_shop_id_shop());
		newProduct.setImage(image.getBytes());
		List<Stock> stockList = new ArrayList<Stock>();
		stockList.add(newStock);
		//newProduct.setImage(null);
		newProduct.setStocks(stockList);
		//return productManager.addProduct(newProduct);
		ProductDto addedProduct = productManager.addProduct(newProduct, shop_id, cat_id);
		/*
		 * if(addedProduct != null) {
		 * newStock.setProduct_id_product(addedProduct.getId());
		 * newStock.setColour("couleur de photo"); stockManager.addStock(newStock); }
		 */
		return addedProduct;
	}
	
	@PutMapping(value = "/update_product/{shop_id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ProductDto updateProduct(@RequestPart(required=true, value="image") MultipartFile image, 
			@RequestPart(required=true, value="product") String product,
			@RequestPart(required=true, value="stock") String stock,
			@PathVariable(name="shop_id") Integer shop_id) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Product newProduct  =	objectMapper.readValue(product, Product.class);
		Stock newStock =  objectMapper.readValue(stock, Stock.class);
		List<Stock> stockList = new ArrayList<Stock>();
		stockList.add(newStock);
		//newProduct.setImage(null);
		newProduct.setStocks(stockList);
		if(image != null) {
			newProduct.setImage(image.getBytes());
		}
		
		return productManager.updateProduct(newProduct, shop_id);
		//return productManager.updateProduct(product);
	}
	
	@DeleteMapping(value = "/delete_product/{product_id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteCategory(@PathVariable ("product_id") Integer product_id) {
		return productManager.deleteProduct(product_id);
	}

}
