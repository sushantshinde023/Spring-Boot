package com.sushant.crud.ecommerce.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;
	
	
	
	public JwtFilter(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = null;
		String username = null;
		
		String authHeader =request.getHeader("Authorization");
		if(authHeader !=null && authHeader.startsWith("Bearer ")) {
			
			token=authHeader.substring(7);
			username = jwtUtil.getUserNameFromJwtToken(token);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			 if (jwtUtil.isTokenValid(token, username)) {
				 UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
				 
				 SecurityContextHolder.getContext().setAuthentication(authToken);
			 }
		}
		
		filterChain.doFilter(request, response);
		
	}

}
