package com.springsecurity.springsecuritydemo.security.jwt;



import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
	
	@Value("${spring.app.jwtSecret}")
	private String jwtSecret;// This will be used for signing the tokens
	
	@Value("${spring.app.jwtExpirationMs}")
	private int jwtExpiration;
	
	// Generate token from username( As we know username will be part of Userdetails object which holds user)
	public String generateTokenFromUsername(UserDetails userDetails) {
		String username=userDetails.getUsername();
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime()+jwtExpiration))
				.signWith(key())
				.compact();
	}
	
	//
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	//getting the token from HTTP Header
		public String getJwtFromHeader(HttpServletRequest request) {
			String bearerToken=request.getHeader("Authorization");//Here Authorization is Header property which holds token
			if(bearerToken!=null && bearerToken.startsWith("Bearer ")) {
				return bearerToken.substring(7);
			}
			return null;
		}
	
	//used to get username from token for validation, decoding
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser()
				.verifyWith((SecretKey) key())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	//validating JWT token
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
			return true;
		}catch(MalformedJwtException e) {
			
		}catch(ExpiredJwtException e) {
			
		}catch(UnsupportedJwtException e) {
			
		}catch(IllegalArgumentException e) {
			
		}
		
		return false;
	}

}
