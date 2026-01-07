package com.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findByAccountUserId(Long userId);

}
