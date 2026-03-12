package com.springsecurity.springsecuritydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
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
	
}
