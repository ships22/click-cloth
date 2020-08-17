package com.clickndcloth.server_side.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clickndcloth.server_side.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	@Query(value = "SELECT user.* FROM user AS user where user.email = ?1", nativeQuery = true)
	User getByEmail(String email);

	@Query(value = "UPDATE user u SET u.is_active = ? WHERE u.id = ?", nativeQuery = true)
	User userStatus(int is_active, Integer id);

}
