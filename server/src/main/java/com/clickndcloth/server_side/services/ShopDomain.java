package com.clickndcloth.server_side.services;

import java.util.List;

import com.clickndcloth.server_side.models.Shop;

public interface ShopDomain {
	
	Shop addShop(Shop shop);
	
	List<Shop> getAllShop();
	
	Shop getByAdminId(int admin_id);
	
	Shop getById(Integer id);
	
	Shop updateShop(Shop shop);
	
	void deleteShop(Integer id);

}
