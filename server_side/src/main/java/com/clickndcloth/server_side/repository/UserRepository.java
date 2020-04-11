package com.clickndcloth.server_side.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickndcloth.server_side.models.User;



public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);

}
