package com.clickndcloth.server_side.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clickndcloth.server_side.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	@Query(value = "SELECT user.* FROM User AS user where user.email = ?1", nativeQuery = true)
	public User getByEmail(String email);

	@Query(value = "update User u set u.is_active = ? where u.id = ?", nativeQuery = true)
	User userStatus(int is_active, Integer id);

	@Query(value = "update User u set u.password = ? where u.id = ?", nativeQuery = true)
	User updateUserPassword(String password, Integer id);
}
