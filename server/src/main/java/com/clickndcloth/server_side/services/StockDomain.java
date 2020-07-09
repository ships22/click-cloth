package com.clickndcloth.server_side.services;

import com.clickndcloth.server_side.models.Stock;

public interface StockDomain {
	
	Stock addStock(Stock shop);
	
	void updateQuantity(int product_sold, int product_id);

}
