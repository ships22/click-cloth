package com.clickndcloth.server_side.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.clickndcloth.server_side.models.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	Client findByEmail(String email);
	
}
