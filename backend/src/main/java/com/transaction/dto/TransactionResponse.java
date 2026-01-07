package com.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {
	
	 private String transactionId;
	 private String type;
	 private BigDecimal amount;
	 private String status;
	 private LocalDateTime transactionTime;

	 private Long accountId;
	 private String accountNumber;
	 
	 

	 public TransactionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public TransactionResponse(String transactionId, String type,
			 BigDecimal amount, String status,
			 LocalDateTime transactionTime,
			 Long accountId, String accountNumber) {
		 this.transactionId = transactionId;
		 this.type = type;
		 this.amount = amount;
		 this.status = status;
		 this.transactionTime = transactionTime;
		 this.accountId = accountId;
		 this.accountNumber = accountNumber;
	    }



	public String getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}



	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}



	public Long getAccountId() {
		return accountId;
	}



	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}



	public String getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	

}
