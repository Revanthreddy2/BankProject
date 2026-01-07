package com.transaction.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.entity.Account;
import com.account.repository.AccountRepository;
import com.common.exception.BusinessException;
import com.common.exception.ResourceNotFoundException;
import com.enums.AccountStatus;
import com.enums.TransactionStatus;
import com.enums.TransactionType;
import com.transaction.dto.TransactionResponse;
import com.transaction.entity.Transaction;
import com.transaction.repository.TransactionRepository;


@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public TransactionResponse credit(Long userId, BigDecimal amount) {

        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().add(amount));

        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setAmount(amount);
        tx.setType(TransactionType.CREDIT);
        tx.setStatus(TransactionStatus.SUCCESS);
        tx.setTransactionTime(LocalDateTime.now());

        transactionRepository.save(tx);

        return new TransactionResponse(
        		tx.getTransactionId(),
                tx.getType().name(),
                tx.getAmount(),
                tx.getStatus().name(),
                tx.getTransactionTime(),
                account.getId(),
                account.getAccountNumber()
        );
    }

    @Override
    public TransactionResponse debit(Long userId, BigDecimal amount) {

        Account account = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        validateAccount(account);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new BusinessException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));

        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setAmount(amount);
        tx.setType(TransactionType.DEBIT);
        tx.setStatus(TransactionStatus.SUCCESS);
        tx.setTransactionTime(LocalDateTime.now());

        transactionRepository.save(tx);

        return new TransactionResponse(
        		tx.getTransactionId(),
                tx.getType().name(),
                tx.getAmount(),
                tx.getStatus().name(),
                tx.getTransactionTime(),
                account.getId(),
                account.getAccountNumber()
        );
    }
    

    @Override
    public List<TransactionResponse> getUserTransactions(Long userId) {

        List<Transaction> transactions =
                transactionRepository.findByAccountUserId(userId);

        return transactions.stream()
                .map(tx -> new TransactionResponse(
                        tx.getTransactionId(),
                        tx.getType().name(),
                        tx.getAmount(),
                        tx.getStatus().name(),
                        tx.getTransactionTime(),
                        tx.getAccount().getId(),
                        tx.getAccount().getAccountNumber()
                ))
                .toList();
    }
    
    private void validateAccount(Account account) {
        if (account.getStatus() == AccountStatus.FROZEN) {
            throw new BusinessException("Account is frozen. Transactions not allowed.");
        }
    }

}
