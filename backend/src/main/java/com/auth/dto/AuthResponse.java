package com.auth.dto;

public class AuthResponse {
	
	private String token;
	private String email;
	private String role;
	private long userId;

	

	public AuthResponse(String token, String email, String role, long userId) {
		super();
		this.token = token;
		this.email = email;
		this.role = role;
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	

}
