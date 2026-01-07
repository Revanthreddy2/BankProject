package com.user.dto;

import com.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequestDto {
	

	@NotBlank(message = "fullName is required")
    private String fullName;

    @NotBlank(message = "email is required")
    @Email
    private String email;

    @NotBlank(message = "mobile is required")
    private String mobile;

    @NotBlank(message = "password is required")
    private String password;

    @NotNull
    private UserRole role;

    
    
	public UserRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserRequestDto(String fullName, String email, String mobile, String password, UserRole role) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.role = role;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
	
    
    
}
