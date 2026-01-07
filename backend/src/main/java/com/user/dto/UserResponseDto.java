package com.user.dto;

import java.time.LocalDateTime;

import com.enums.UserRole;
import com.enums.UserStatus;

public class UserResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private String mobile;
    private UserRole role;
    private UserStatus status;
    private boolean verified;
    private LocalDateTime createdAt;
	public UserResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public UserResponseDto(Long id, String fullName, String email, String mobile, UserRole role, UserStatus status,
			boolean verified, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
		this.status = status;
		this.verified = verified;
		this.createdAt = createdAt;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
