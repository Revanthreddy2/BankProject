package com.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByUserId(Long userId);

	boolean existsByUserId(Long userId);
	
	
}
