package com.account.dto;

import java.math.BigDecimal;

import com.enums.AccountStatus;

public class AccountResponse {
	
	private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private AccountStatus status;
    private Long userId;
    
    
	public AccountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AccountResponse(Long id, String accountNumber, BigDecimal balance, AccountStatus status, Long userId) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.status = status;
		this.userId = userId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	public AccountStatus getStatus() {
		return status;
	}


	public void setStatus(AccountStatus status) {
		this.status = status;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}
    
    

}
