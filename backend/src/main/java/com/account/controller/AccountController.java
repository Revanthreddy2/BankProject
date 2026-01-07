package com.account.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

//import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.dto.AccountResponse;
import com.account.service.AccountService;


@CrossOrigin
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

   
    // CREATE ACCOUNT

    @PostMapping("/create/{userId}")
    public ResponseEntity<AccountResponse> createAccount(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.createAccount(userId));
    }

     
    // GET ACCOUNT DETAILS
    @GetMapping("/{userId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccount(userId));
    }

    @PutMapping("/freeze/{userId}")
    public ResponseEntity<Map<String, String>> freezeAccount(@PathVariable Long userId) {

        accountService.freezeAccount(userId);

        return ResponseEntity.ok(
            Map.of("message", "Account frozen successfully")
        );
    }
    
    @PutMapping("/unfreeze/{userId}")
    public ResponseEntity<Map<String, String>> unfreezeAccount(@PathVariable Long userId) {

        accountService.unfreezeAccount(userId);

        return ResponseEntity.ok(
            Map.of("message", "Account unfrozen successfully")
        );
    }

//    @PutMapping("/unfreeze/{userId}")
//    public Account unfreeze(@PathVariable Long userId) {
//        return accountService.unfreezeAccount(userId);
//    }


}
