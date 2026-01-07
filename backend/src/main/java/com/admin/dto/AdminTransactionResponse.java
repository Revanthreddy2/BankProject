package com.admin.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.enums.TransactionStatus;
import com.enums.TransactionType;

public class AdminTransactionResponse {
	
	private Long transactionId;
    private Long userId;
    private String accountNumber;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private LocalDateTime transactionTime;
    
	public AdminTransactionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminTransactionResponse(Long transactionId, Long userId, String accountNumber, BigDecimal amount,
			TransactionType type, TransactionStatus status, LocalDateTime transactionTime) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.type = type;
		this.status = status;
		this.transactionTime = transactionTime;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	
    
    

}
