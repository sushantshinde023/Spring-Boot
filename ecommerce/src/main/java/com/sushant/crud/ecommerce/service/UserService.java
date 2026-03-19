package com.sushant.crud.ecommerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sushant.crud.ecommerce.dto.LoginRequest;
import com.sushant.crud.ecommerce.dto.UserRegisterDto;
import com.sushant.crud.ecommerce.entity.Role;
import com.sushant.crud.ecommerce.entity.User;
import com.sushant.crud.ecommerce.exception.ResourceNotFoundException;
import com.sushant.crud.ecommerce.repository.UserRepository;
import com.sushant.crud.ecommerce.security.JwtUtil;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil=jwtUtil;
	}
	
	
	public void register(UserRegisterDto dto) {
		
		User user=new User();
		user.setEmail(dto.email());
		user.setName(dto.name());
		user.setPassword(passwordEncoder.encode(dto.password()));
		
		user.setRole(Role.USER);

        userRepository.save(user);
		
	}


	public String login(LoginRequest dto) {
		// TODO Auto-generated method stub
		
		User user=userRepository.findByEmail(dto.email())
				.orElseThrow(()-> new ResourceNotFoundException("User not found with Emai; "+dto.email()));
		
		if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
	        throw new RuntimeException("Invalid credentials");
	    }
		
		 return jwtUtil.generateToken(user.getEmail());
		
	}

}
