package com.admin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.AdminTransactionResponse;
import com.admin.dto.AdminUserResponse;
import com.admin.service.AdminService;


@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
	
	@GetMapping("/users")
	public List<AdminUserResponse> getAllUsers() {
		return adminService.getAllUsers();
	}

	@PutMapping("/users/{id}/freeze")
	public AdminUserResponse freezeUser(@PathVariable Long id) {
		return adminService.freezeUser(id);
	}

	@PutMapping("/users/{id}/unfreeze")
	public AdminUserResponse unfreezeUser(@PathVariable Long id) {
		return adminService.unfreezeUser(id);
	}

	@GetMapping("/transactions")
	public List<AdminTransactionResponse> getAllTransactions() {
		return adminService.getAllTransactions();
	}

}
