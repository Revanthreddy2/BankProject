package com.transaction.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.dto.TransactionResponse;
import com.transaction.service.TransactionService;

@CrossOrigin
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	
	@Autowired
    private TransactionService transactionService;

	@PostMapping("/credit/{userId}")
	public ResponseEntity<TransactionResponse> credit(
	        @PathVariable Long userId,
	        @RequestParam BigDecimal amount) {

	    return ResponseEntity.ok(transactionService.credit(userId, amount));
	}

	@PostMapping("/debit/{userId}")
	public ResponseEntity<TransactionResponse> debit(
	        @PathVariable Long userId,
	        @RequestParam BigDecimal amount) {

	    return ResponseEntity.ok(transactionService.debit(userId, amount));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<TransactionResponse>> getTransactions(
	        @PathVariable Long userId) {

	    return ResponseEntity.ok(transactionService.getUserTransactions(userId));
	}

}
