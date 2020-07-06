package com.clickndcloth.server_side.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clickndcloth.server_side.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

	List<Reservation>findAllByClientId(int client_id);
	
	List<Reservation>findAllByShopId(int shop_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Reservation As res SET res.status =:status WHERE res.reservation_id  =:reservation_id", nativeQuery = true)
	void updateStatus(@Param("status") String status , @Param("reservation_id") int reservation_id);
	
}
