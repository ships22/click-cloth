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

import com.clickndcloth.server_side.application.ShopManager;
import com.clickndcloth.server_side.dto.ShopDto;
import com.clickndcloth.server_side.models.Shop;


@RestController
@RequestMapping(value = "/api")
public class ShopController {
	
	@Autowired
	private ShopManager shopManager;

	@GetMapping(value = "/shops")
	public List<ShopDto> getAllShop() {
		return shopManager.getAllShop();
	}
	

	@GetMapping(value = "/shop_by_id/{id}")
	public ShopDto getShopById(@PathVariable ("id") Integer id) {
		return shopManager.findShopById(id);
	}
	
	@GetMapping(value = "/shop_by_admin_id/{admin_id}")
	public ShopDto getShopByAdminId(@PathVariable ("admin_id") Integer admin_id) {
		return shopManager.findShopByAdminId(admin_id);
	}
	
	@PostMapping(value = "/add_shop/{admin_id}/shop", produces = "application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ShopDto addShop(@RequestBody Shop shop, @PathVariable ("admin_id") Integer admin_id) {
		return shopManager.addShop(shop, admin_id);
	}
	
	@PutMapping(value = "/update_shop/{admin_id}/shop/{shop_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ShopDto updateShop(@RequestBody Shop shop, @PathVariable ("admin_id") Integer admin_id, @PathVariable ("shop_id") Integer shop_id) {
		return shopManager.updateShop(shop, admin_id, shop_id);
	}
	
	@DeleteMapping(value = "/delete_shop/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteShop(@PathVariable ("id") Integer id) {
		return shopManager.deleteShop(id);
	}
	
	

}
