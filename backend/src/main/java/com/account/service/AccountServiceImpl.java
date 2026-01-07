package com.account.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.dto.AccountResponse;
import com.account.entity.Account;
import com.account.repository.AccountRepository;
import com.common.exception.BusinessException;
//import com.common.exception.BusinessException;
import com.common.exception.ResourceNotFoundException;
import com.enums.AccountStatus;
import com.user.entity.User;
import com.user.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public AccountResponse createAccount(Long userId) {

	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	    
	 //CHECK IF ACCOUNT ALREADY EXISTS
	    if (accountRepository.existsByUserId(userId)) {
	        throw new BusinessException("Account already exists for this user");
	    }

	    Account account = new Account();
	    account.setUser(user);
	    account.setBalance(BigDecimal.ZERO);
	    account.setStatus(AccountStatus.ACTIVE);
	    account.setAccountNumber("ACC" + System.currentTimeMillis());

	    Account saved = accountRepository.save(account);

	    return new AccountResponse(
	            saved.getId(),
	            saved.getAccountNumber(),
	            saved.getBalance(),
	            saved.getStatus(),
	            user.getId()
	    );
	}

	    @Override
	    public AccountResponse getAccount(Long userId) {

	        Account account = accountRepository.findByUserId(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

	        return new AccountResponse(
	                account.getId(),
	                account.getAccountNumber(),
	                account.getBalance(),
	                account.getStatus(),
	                account.getUser().getId()
	        );
	    }

	    @Override
	    public void freezeAccount(Long userId) {
	    	 Account account = accountRepository.findByUserId(userId)
	    	            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
	    	 
	        account.setStatus(AccountStatus.FROZEN);
	        accountRepository.save(account);
	    }

	  

		@Override
	    public void unfreezeAccount(Long userId) {
			 Account account = accountRepository.findByUserId(userId)
			            .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
			 
	        account.setStatus(AccountStatus.ACTIVE);
	        accountRepository.save(account);
	    }


}
