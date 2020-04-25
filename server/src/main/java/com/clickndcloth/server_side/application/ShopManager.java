package com.clickndcloth.server_side.application;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.dto.ShopDto;
import com.clickndcloth.server_side.models.Shop;
import com.clickndcloth.server_side.services.ShopDomainServiceImpl;

@Service
public class ShopManager {
	
	@Autowired
	ShopDomainServiceImpl shopDomainService;
	
	@Transactional
	public ShopDto addShop(Shop shop, int admin_id) {
		
		shop.setAdmin_id_admin(admin_id);
		shop.setIs_active(1);
		
		Shop addedShop = shopDomainService.addShop(shop);
		
		ShopDto shopDto = new ShopDto();
		shopDto.setId(addedShop.getId());
		shopDto.setName(addedShop.getName());
		shopDto.setAddress(addedShop.getAddress());
		shopDto.setEmail(addedShop.getEmail());
		shopDto.setPhone(addedShop.getPhone());
		shopDto.setIs_active(addedShop.getIs_active());
		return shopDto;
	}
	
	@Transactional
	public List<ShopDto>getAllShop() {
		List<Shop>shopList = shopDomainService.getAllShop();
		List<ShopDto>shopDtos = new ArrayList<ShopDto>();
		shopList.forEach(shop -> {
			ShopDto shopDto = new ShopDto();
			shopDto.setId(shop.getId());
			shopDto.setName(shop.getName());
			shopDto.setAddress(shop.getAddress());
			shopDto.setEmail(shop.getEmail());
			shopDto.setPhone(shop.getPhone());
			shopDto.setIs_active(shop.getIs_active());
		});
		return shopDtos;
	}
	
	@Transactional
	public ShopDto findShopById(Integer id) {
		Shop shop = shopDomainService.getById(id);
		ShopDto shopDto = new ShopDto();
		shopDto.setId(shop.getId());
		shopDto.setName(shop.getName());
		shopDto.setAddress(shop.getAddress());
		shopDto.setEmail(shop.getEmail());
		shopDto.setPhone(shop.getPhone());
		shopDto.setIs_active(shop.getIs_active());
		return shopDto;
	}
	
	@Transactional
	public ShopDto updateShop(Shop shop) {
		Shop updatedShop = shopDomainService.updateShop(shop);
		ShopDto shopDto = new ShopDto();
		shopDto.setId(shop.getId());
		shopDto.setName(updatedShop.getName());
		shopDto.setAddress(updatedShop.getAddress());
		shopDto.setEmail(updatedShop.getEmail());
		shopDto.setPhone(updatedShop.getPhone());
		shopDto.setIs_active(shop.getIs_active());
		return shopDto;
	}
	
	@Transactional
	public String deleteShop(Integer id) {
		shopDomainService.deleteShop(id);
		return "Shop with id : " + id + " has been deleted succesfully";
	}

}