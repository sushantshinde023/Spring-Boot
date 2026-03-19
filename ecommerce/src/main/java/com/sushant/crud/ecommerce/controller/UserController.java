package com.sushant.crud.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushant.crud.ecommerce.dto.LoginRequest;
import com.sushant.crud.ecommerce.dto.UserRegisterDto;
import com.sushant.crud.ecommerce.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	
	
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}



	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRegisterDto dto) {
	    userService.register(dto);
	    return ResponseEntity.ok("User Registered Successfully");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> register(@RequestBody LoginRequest dto) {
	    String token =userService.login(dto);
	    return ResponseEntity.ok(token);
	}
}
