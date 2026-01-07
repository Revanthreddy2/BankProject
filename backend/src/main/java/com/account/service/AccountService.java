package com.account.service;

import com.account.dto.AccountResponse;

//import java.math.BigDecimal;


public interface AccountService {
	
	AccountResponse createAccount(Long userId);

	AccountResponse getAccount(Long userId);

    void freezeAccount(Long userId);

    void unfreezeAccount(Long userId);

}
