package com.clickndcloth.server_side.testunitaires;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.clickndcloth.server_side.application.ProductManager;
import com.clickndcloth.server_side.controller.ProductController;
import com.clickndcloth.server_side.models.Product;
import com.clickndcloth.server_side.services.ProductDomainServiceImpl;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class Productunittest {
	
	@Autowired
	private ProductManager productManager;
	
	@MockBean
	private ProductDomainServiceImpl productService;

	//ProductController productController = new ProductController(); // Arrange
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetAllProduct() {
		when(productService.getAllProduct()).thenReturn((List<Product>) Stream.of(
				new Product()).collect(Collectors.toList()));
		assertEquals(1, productManager.getAllProduct().size());
	}
	
	@Test
	void testAddProduct() {
		Product product = new Product();
		when(productService.addProduct(product)).thenReturn(product);
		assertEquals(product, productService.addProduct(product));
	}
	
	@Test
	void testDeleteProduct() {
		int idProduct = 9;
		productManager.deleteProduct(idProduct);
		verify(productService, times(1)).deleteProduct(idProduct);
	}
	

}
