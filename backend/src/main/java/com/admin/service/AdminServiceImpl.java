package com.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.account.repository.AccountRepository;
import com.admin.dto.AdminTransactionResponse;
import com.admin.dto.AdminUserResponse;
import com.common.exception.ResourceNotFoundException;
import com.enums.UserStatus;
import com.transaction.repository.TransactionRepository;
import com.user.entity.User;
import com.user.repository.UserRepository;


@Service
public class AdminServiceImpl implements AdminService{
	
	private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public AdminServiceImpl(UserRepository userRepository,
                            TransactionRepository transactionRepository,
                            AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

	@Override
	public List<AdminUserResponse> getAllUsers() {
		return userRepository.findAll()
                .stream()
                .map(user -> new AdminUserResponse(
                        user.getId(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getMobile(),
                        user.getRole(),
                        user.getStatus(),
                        user.isVerified()))
                .toList();
	}

	@Override
	public AdminUserResponse freezeUser(Long userId) {
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setStatus(UserStatus.FROZEN);
        userRepository.save(user);

        return new AdminUserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getMobile(),
                user.getRole(),
                user.getStatus(),
                user.isVerified()
        );
	}

	@Override
	public AdminUserResponse unfreezeUser(Long userId) {
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);

        return new AdminUserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getMobile(),
                user.getRole(),
                user.getStatus(),
                user.isVerified()
        );
	}

	@Override
	public List<AdminTransactionResponse> getAllTransactions() {
		return transactionRepository.findAll()
                .stream()
                .map(tx -> new AdminTransactionResponse(
                        tx.getId(),
                        tx.getAccount().getUser().getId(),
                        tx.getAccount().getAccountNumber(),
                        tx.getAmount(),
                        tx.getType(),
                        tx.getStatus(),
                        tx.getTransactionTime()
                ))
                .toList();
	}
	
	

}
