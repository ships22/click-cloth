package com.clickndcloth.server_side.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clickndcloth.server_side.models.Shop;
import com.clickndcloth.server_side.repository.ShopRepository;

@Component
public class ShopDomainServiceImpl implements ShopDomain {
	
	@Autowired
	ShopRepository shopRepository;

	@Override
	public Shop addShop(Shop shop) {
		return shopRepository.save(shop);
	}

	@Override
	public List<Shop> getAllShop() {
		return shopRepository.findAll(); 
	}

	@Override
	public Shop getById(Integer id) {
		return shopRepository.getOne(id);
	}
	
	@Override
	public Shop getByAdminId(int admin_id) {
		return shopRepository.findByAdminId(admin_id);
	}

	@Override
	public Shop updateShop(Shop shop) {
		return shopRepository.saveAndFlush(shop);
	}

	@Override
	public void deleteShop(Integer id) {
		 shopRepository.deleteById(id);
	}

}
