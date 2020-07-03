package com.clickndcloth.server_side.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clickndcloth.server_side.models.Reservation;
import com.clickndcloth.server_side.repository.ReservationRepository;

@Component
public class ReservationDomainServiceImpl implements ReservationDomain {
	
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public Reservation addReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> getAllByClient(int client_id) {
		return reservationRepository.findAllByClientId(client_id);
	}

	@Override
	public List<Reservation> getAllByShop(int shop_id) {
		return reservationRepository.findAllByShopId(shop_id);
	}

}
