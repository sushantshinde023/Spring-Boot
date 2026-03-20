package com.sushant.crud.ecommerce.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mysecretkey12fhgbajsuidghfsdjgfsdgjf233gasdjhjasdhjg";

    private Key key() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String userName) {
        return Jwts.builder()
                   .setSubject(userName)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                   .signWith(key(), SignatureAlgorithm.HS256)
                   .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(key())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    public String getUserNameFromJwtToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, String username) {
        return username.equals(getUserNameFromJwtToken(token)) && !isTokenExpired(token);
    }
}
