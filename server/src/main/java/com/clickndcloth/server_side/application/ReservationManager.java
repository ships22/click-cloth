package com.clickndcloth.server_side.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.dto.ReservationDto;
import com.clickndcloth.server_side.dto.ShopDto;
import com.clickndcloth.server_side.exception.NotFoundException;
import com.clickndcloth.server_side.models.Client;
import com.clickndcloth.server_side.models.Product;
import com.clickndcloth.server_side.models.Reservation;
import com.clickndcloth.server_side.models.Shop;
import com.clickndcloth.server_side.repository.ProductRepository;
import com.clickndcloth.server_side.services.ClientDomainServiceImpl;
import com.clickndcloth.server_side.services.ProductDomainServiceImpl;
import com.clickndcloth.server_side.services.ReservationDomainServiceImpl;
import com.clickndcloth.server_side.services.ShopDomainServiceImpl;

@Service
public class ReservationManager {

	@Autowired
	private ReservationDomainServiceImpl reservationService;
	
	@Autowired
	private ClientDomainServiceImpl clientService;
	
	@Autowired
	private ProductDomainServiceImpl productService;
	
	@Autowired
	private ShopDomainServiceImpl shopService;
	

	@Autowired
	private ProductRepository pro;
	
	@Transactional
	public ReservationDto doReservation(Reservation reservation, int product_id, int shop_id) {
		
		//getting all the reserved product -
		reservation.setShop(shopService.getById(shop_id));
		reservation.setProduct(productService.findById(product_id).get());
		
		//finalising the reservation -
		Reservation addedReservation = reservationService.addReservation(reservation);
		ReservationDto reservationDto = new ReservationDto();
		reservationDto.setReservation_id(addedReservation.getReservation_id());
		reservationDto.setDate_time(addedReservation.getDate_time());
		reservationDto.setQuantity(addedReservation.getQuantity());
		reservationDto.setReference(addedReservation.getReference());
		reservationDto.setClient(addedReservation.getClient());
		reservationDto.setProduct(addedReservation.getProduct());
		reservationDto.setTotal(addedReservation.getTotal());
		reservationDto.setStatus(addedReservation.getStatus());
		return reservationDto;
	}
}
