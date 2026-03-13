package com.springsecurity.springsecuritydemo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.springsecuritydemo.entity.LoginResponse;
import com.springsecurity.springsecuritydemo.entity.LoginUser;
import com.springsecurity.springsecuritydemo.security.jwt.JwtUtils;

@RestController
public class Controller {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello user ..!";
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String sayHelloUser() {
		return "Hello user ..!";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String sayHelloAdmin() {
		return "Hello admin ..!";
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginUser loginRequest){
		
		Authentication authentication;
		
		//We are doing authentication
		authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		//Once User is Authenticated we are setting SecurityContext for the session
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails=(UserDetails) authentication.getPrincipal();
		String jwtToken=jwtUtils.generateTokenFromUsername(userDetails);
		//Roles for response if not needed we can remove from Rsponse class
		List<String> roles=userDetails.getAuthorities().stream().map(item->item.getAuthority()).collect(Collectors.toList());
		
		LoginResponse response=new LoginResponse(jwtToken,userDetails.getUsername(),roles);
		return ResponseEntity.ok(response);
		
	}
}
