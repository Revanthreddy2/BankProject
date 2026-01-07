package com.transaction.service;

import java.math.BigDecimal;
import java.util.List;

import com.transaction.dto.TransactionResponse;


public interface TransactionService {

	TransactionResponse credit(Long userId, BigDecimal amount);

	TransactionResponse debit(Long userId, BigDecimal amount);

	List<TransactionResponse> getUserTransactions(Long userId);
}
