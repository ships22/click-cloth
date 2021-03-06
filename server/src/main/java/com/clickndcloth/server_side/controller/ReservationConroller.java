package com.clickndcloth.server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.clickndcloth.server_side.application.ClientManager;
import com.clickndcloth.server_side.application.ProductManager;
import com.clickndcloth.server_side.application.ReservationManager;
import com.clickndcloth.server_side.dto.ProductDto;
import com.clickndcloth.server_side.dto.ReservationDto;
import com.clickndcloth.server_side.models.Client;
import com.clickndcloth.server_side.models.Reservation;
import com.clickndcloth.server_side.models.Stock;
import com.clickndcloth.server_side.repository.ClientRepository;
import com.clickndcloth.server_side.repository.ProductRepository;
import com.clickndcloth.server_side.repository.ReservationRepository;
import com.clickndcloth.server_side.repository.ShopRepository;
import com.clickndcloth.server_side.repository.StockRepository;

@RestController
@RequestMapping(value = "/api")
public class ReservationConroller {
	
	@Autowired
	private ReservationManager reservationManager;
	
	@Autowired
	private ReservationRepository res;
	
	@Autowired
	private ShopRepository sho;
	
	@Autowired
	private StockRepository stRepo;
	
	@RequestMapping(value = "/do_reservation/{product_id}/shop/{shop_id}", method = RequestMethod.POST, produces = "application/json")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public ReservationDto doReservation(@RequestBody Reservation reservation, 
			@PathVariable("product_id") int product_id,
			@PathVariable("shop_id") int shop_id) { 
			return reservationManager.doReservation(reservation, product_id, shop_id);
	}
	
	@GetMapping(value = "/reservationByClient/{client_id}")
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	public List <Reservation> getReservationByClientId(@PathVariable ("client_id") int client_id) {
		return res.findAllByClientId(client_id);
	}

	@GetMapping(value = "/reservationsByShop/{shop_id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List <ReservationDto> getReservationByShopId(@PathVariable ("shop_id") int shop_id) {
		return reservationManager.getAllByShop(shop_id);
	}
	
	@RequestMapping(value = "/update/{status}/reservation/{reservation_id}/product/{product_sold}/stock/{id}", method = RequestMethod.POST, produces = "application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String updateStock(@PathVariable("status") String status, @PathVariable ("reservation_id") int reservation_id, 
			@PathVariable ("product_sold") int product_sold, @PathVariable("id") int id) {
		 
		return reservationManager.reservationAndStockUpdate(id, product_sold, reservation_id, status);
		 
	}

}
