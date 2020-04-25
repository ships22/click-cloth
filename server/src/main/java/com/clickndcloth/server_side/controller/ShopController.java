package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickndcloth.server_side.application.ShopManager;
import com.clickndcloth.server_side.dto.ClientDto;
import com.clickndcloth.server_side.dto.ShopDto;
import com.clickndcloth.server_side.models.Client;
import com.clickndcloth.server_side.models.Shop;


@RestController
@RequestMapping(value = "/api")
public class ShopController {
	
	@Autowired
	private ShopManager shopManager;

	@GetMapping(value = "/shops")
	public List<ShopDto> getAllClient() {
		return shopManager.getAllShop();
	}
	

	@GetMapping(value = "/shop_by_id/{id}")
	public ShopDto getShopById(@PathVariable ("id") Integer id) {
		return shopManager.findShopById(id);
	}
	
	@PostMapping(value = "/add_shop/{admin_id}", produces = "application/json")
	public ShopDto addShop(@RequestBody Shop shop, @PathVariable ("admin_id") int admin_id) {
		return shopManager.addShop(shop, admin_id);
		
	}
	
	@PutMapping(value = "/update_shop")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ShopDto updateShop(@RequestBody Shop shop) {
		return shopManager.updateShop(shop);
	}
	
	@DeleteMapping(value = "/delete_shop/{id}")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteShop(@PathVariable ("id") Integer id) {
		return shopManager.deleteShop(id);
	}
	
	

}
