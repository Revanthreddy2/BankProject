package com.admin.service;

import java.util.List;

import com.admin.dto.AdminTransactionResponse;
import com.admin.dto.AdminUserResponse;

public interface AdminService {
	
	List<AdminUserResponse> getAllUsers();

    AdminUserResponse freezeUser(Long userId);

    AdminUserResponse unfreezeUser(Long userId);

    List<AdminTransactionResponse> getAllTransactions();

}
