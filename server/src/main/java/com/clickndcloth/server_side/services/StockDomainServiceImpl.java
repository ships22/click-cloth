package com.clickndcloth.server_side.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clickndcloth.server_side.models.Stock;
import com.clickndcloth.server_side.repository.StockRepository;

@Component
public class StockDomainServiceImpl implements StockDomain {
	
	@Autowired
	private StockRepository stockRepository;

	@Override
	public Stock addStock(Stock stock) {
		return stockRepository.save(stock);
	}

}
