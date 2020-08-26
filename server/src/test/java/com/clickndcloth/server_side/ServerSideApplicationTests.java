package com.clickndcloth.server_side;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.clickndcloth.server_side.application.ProductManager;
import com.clickndcloth.server_side.services.ProductDomainServiceImpl;



@ExtendWith(SpringExtension.class)
@SpringBootTest
class ServerSideApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private ProductManager productManager;
	
	@MockBean
	private ProductDomainServiceImpl productService;

	
}
