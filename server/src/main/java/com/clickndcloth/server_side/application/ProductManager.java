package com.clickndcloth.server_side.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.dto.ProductDto;
import com.clickndcloth.server_side.dto.ShopDto;
import com.clickndcloth.server_side.exception.NotFoundException;
import com.clickndcloth.server_side.models.Product;
import com.clickndcloth.server_side.models.Shop;
import com.clickndcloth.server_side.services.ProductDomainServiceImpl;
import com.clickndcloth.server_side.services.ShopDomainServiceImpl;

@Service
public class ProductManager {
	
	@Autowired
	private ProductDomainServiceImpl productDomainService;
	
	@Autowired 
	private ShopDomainServiceImpl shopDomainService;
	
	@Transactional
	public ProductDto addProduct(Product product, int shop_id) {
		
		Shop shop = shopDomainService.getById(shop_id);
		product.setShop(shop);

		Product addedProduct = productDomainService.addProduct(product);
		ProductDto productDto = new ProductDto();
		productDto.setId(addedProduct.getId());
		productDto.setName(addedProduct.getName());
		productDto.setPrice(addedProduct.getPrice());
		productDto.setDescription(addedProduct.getDescription());
		productDto.setDiscount(addedProduct.getDiscount());
		productDto.setImage(addedProduct.getImage());
		
		
		
	/*	.map(shop -> {
			
			shop.setAdmin(admin);
			Shop addedShop = new Shop();
			addedShop = shopDomainService.addShop(shop);
			ShopDto shopDto = new ShopDto();
			shopDto.setShop_id(addedShop.getShop_id());
			shopDto.setName(addedShop.getName());
			shopDto.setAddress(addedShop.getAddress());
			shopDto.setEmail(addedShop.getEmail());
			shopDto.setPhone(addedShop.getPhone());
			shopDto.setIs_active(addedShop.getIs_active());
			return shopDto;
		}).orElseThrow(() -> new NotFoundException("Admin not found"));
		return null;*/
		
		
		/*
		 * Product addedProduct = productDomainService.addProduct(product); ProductDto
		 * productDto = new ProductDto(); productDto.setId(addedProduct.getId());
		 * productDto.setName(addedProduct.getName());
		 * productDto.setPrice(addedProduct.getPrice());
		 * productDto.setDescription(addedProduct.getDescription());
		 * productDto.setDiscount(addedProduct.getDiscount());
		 * productDto.setImage(addedProduct.getImage());
		 */
		//productDto.setShop_admin_id_admin(addedProduct.getShop_admin_id_admin());
		//productDto.setShop_id_shop(addedProduct.getShop_id_shop());
		//productDto.setCategories(addedProduct.getCategories());
		return productDto;
	}
	
	@Transactional
	public List<ProductDto>getAllProduct() {
		List<Product>productList = productDomainService.getAllProduct();
		List<ProductDto>productDtos = new ArrayList<ProductDto>();
		productList.forEach(product -> {
			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setPrice(product.getPrice());
			productDto.setDescription(product.getDescription());
			productDto.setDiscount(product.getDiscount());
			productDto.setImage(product.getImage());
			//productDto.setShop_admin_id_admin(product.getShop_admin_id_admin());
			//productDto.setShop_id_shop(product.getShop_id_shop());
			productDto.setCategories(product.getCategories());
			productDto.setStock(product.getStocks());
			productDtos.add(productDto);
		});
		return productDtos;
	}
	
	@Transactional
	public ProductDto getProductById(Integer id) {
		Product product = productDomainService.getProductById(id);
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		productDto.setDescription(product.getDescription());
		productDto.setDiscount(product.getDiscount());
		productDto.setImage(product.getImage());
		//productDto.setShop_admin_id_admin(product.getShop_admin_id_admin());
		//productDto.setShop_id_shop(product.getShop_id_shop());
		productDto.setCategories(product.getCategories());
		productDto.setStock(product.getStocks());
		return productDto;
	}
	
	@Transactional
	public List<ProductDto>getProductsByShop(int shop_id) {
		List<Product>productList = productDomainService.getProductsByShopId(shop_id);
		List<ProductDto>productDtos = new ArrayList<ProductDto>();
		productList.forEach(product -> {
			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setPrice(product.getPrice());
			productDto.setDescription(product.getDescription());
			productDto.setDiscount(product.getDiscount());
			productDto.setImage(product.getImage());
			//productDto.setShop_admin_id_admin(product.getShop_admin_id_admin());
			//productDto.setShop_id_shop(product.getShop_id_shop());
			productDto.setCategories(product.getCategories());
			productDto.setStock(product.getStocks());
			productDtos.add(productDto);
		});
		return productDtos;
	}
	
	@Transactional
	public ProductDto updateProduct(Product product) {
		Product updatedProduct = productDomainService.updateProduct(product);
		ProductDto productDto = new ProductDto();
		productDto.setId(updatedProduct.getId());
		productDto.setName(updatedProduct.getName());
		productDto.setPrice(updatedProduct.getPrice());
		productDto.setDescription(updatedProduct.getDescription());
		productDto.setDiscount(updatedProduct.getDiscount());
		productDto.setImage(updatedProduct.getImage());
		//productDto.setShop_admin_id_admin(updatedProduct.getShop_admin_id_admin());
		//productDto.setShop_id_shop(updatedProduct.getShop_id_shop());
		productDto.setCategories(product.getCategories());
		productDto.setStock(product.getStocks());
		return productDto;
	}
	
	@Transactional
	public String deleteProduct(Integer id) {
		productDomainService.deleteProduct(id);
		return "Product with id : " + id + " has been deleted succesfully";
	}
	

}
