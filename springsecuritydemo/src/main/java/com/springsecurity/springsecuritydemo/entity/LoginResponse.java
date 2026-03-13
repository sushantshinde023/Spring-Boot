package com.springsecurity.springsecuritydemo.entity;

import java.util.List;

public class LoginResponse {
	
	private String jwtToken;
	
	private String usernaeme;
	private List<String> roles;
	
	public LoginResponse() {
		
	}

	public LoginResponse(String jwtToken, String usernaeme, List<String> roles) {
		super();
		this.jwtToken = jwtToken;
		this.usernaeme = usernaeme;
		this.roles = roles;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUsernaeme() {
		return usernaeme;
	}

	public void setUsernaeme(String usernaem) {
		this.usernaeme = usernaem;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	

}
