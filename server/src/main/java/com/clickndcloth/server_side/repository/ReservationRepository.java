package com.clickndcloth.server_side.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clickndcloth.server_side.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

	List<Reservation>findAllByClientId(int client_id);
	
	List<Reservation>findAllByShopId(int shop_id);
	
}
