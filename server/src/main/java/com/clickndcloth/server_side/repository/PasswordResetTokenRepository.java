package com.clickndcloth.server_side.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickndcloth.server_side.models.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{

	PasswordResetToken findByToken(String token);

}
