package com.clickndcloth.server_side.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.clickndcloth.server_side.dto.StockDto;

import com.clickndcloth.server_side.models.Stock;
import com.clickndcloth.server_side.services.StockDomainServiceImpl;

@Service
public class StockManager {
	
	@Autowired
	private StockDomainServiceImpl stockDomainService;

	@Transactional
	public StockDto addStock(Stock stock) {
		Stock addedStock = stockDomainService.addStock(stock);
		StockDto stockDto = new StockDto();
		stockDto.setId(addedStock.getId());
		stockDto.setSize(addedStock.getSize());
		stockDto.setQuantite(addedStock.getQuantite());
		return stockDto;
	}
	
}
