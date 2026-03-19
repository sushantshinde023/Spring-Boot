package com.sushant.crud.ecommerce.security;

import java.util.Date;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final String SECRET_KEY = "mysecretkey12fhgbajsui233gasdjhjasdhjg";
	
	public String generateToken(String userName) {
		return Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), io.jsonwebtoken.SignatureAlgorithm.HS256)
				.compact();
	}
}
