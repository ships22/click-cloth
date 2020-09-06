package com.clickndcloth.server_side.testunitaires;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.clickndcloth.server_side.application.AdminManager;
import com.clickndcloth.server_side.dto.AdminDto;
import com.clickndcloth.server_side.models.Admin;
import com.clickndcloth.server_side.models.Product;
import com.clickndcloth.server_side.services.AdminDomainServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Adminunittest {
	@Autowired
	AdminManager adminManager;
	@MockBean
	private AdminDomainServiceImpl adminService;
	
	// test get all admin -
	@SuppressWarnings("unchecked")
	@Test
	void testGetAllAdmin() {
		Admin admin = new Admin();
		admin.setId(111);
		admin.setFirst_name("test first name");
		admin.setLast_name("test last name");
		admin.setEmail("test@mail.com");
		admin.setAddress("55 rue de vincennes, montreuil, 93100");
		when(adminService.getAllAdmin()).thenReturn((List<Admin>) Stream.of(admin).collect(Collectors.toList()));
		assertEquals(1 , adminManager.getAllAdmin().size());
	}
	
	// test get add admin -
	@Test 
	void testAddAdmin() {
		Admin admin = new Admin();
		admin.setFirst_name("test first name");
		admin.setLast_name("test last name");
		admin.setEmail("test@mail.com");
		admin.setAddress("55 rue de vincennes, montreuil, 93100");
		when(adminService.addAdmin(admin)).thenReturn(admin);
		assertEquals(admin, adminService.addAdmin(admin));
	}
	
	// test get delete admin -
	@Test
	void testDeleteAdmin() {
		int idAdmin = 5;
		adminManager.deleteAdmin(idAdmin);
		verify(adminService, times(1)).deleteAdmin(idAdmin);
	}

}
