package com.clickndcloth.server_side.services;

import java.util.List;

import com.clickndcloth.server_side.models.Reservation;

public interface ReservationDomain {
	
	Reservation addReservation(Reservation reservation);
	
	List<Reservation>getAllByClient(int client_id);
	
	List<Reservation>getAllByShop(int shop_id);

}
