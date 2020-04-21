package com.clickndcloth.server_side.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.clickndcloth.server_side.dto.UserDto;
import com.clickndcloth.server_side.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	
	@Autowired
	private Constant constant;
	
	private String SECRET_KEY = "ships";
	
	
	public String generateToken(UserDetails userDetails, UserDto userDto) {
//		Map<String, Object> claims = new HashMap<>();
//		return createToken(claims, userDetails.getUsername(), constant.loginTokenTime);
		return doGenerateToken(userDetails, userDto, constant.loginTokenTime);
	}
	
	public String generateResetPasswordToken(String email) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, email, constant.resetPasswordTime);
	}
	
	private String createToken(Map<String, Object> claims, String subject, int duration) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + duration))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	private String doGenerateToken(UserDetails userDetails, UserDto userDto, int duration) {
		Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
		claims.put("scopes", userDetails.getAuthorities());
		claims.put("user_name", userDetails.getUsername());
		
			claims.put("first_name", userDto.getFirst_name());
		
//			claims.put("id_client", user.getClient_id_client());	
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + duration))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) throws ExpiredJwtException {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
}
