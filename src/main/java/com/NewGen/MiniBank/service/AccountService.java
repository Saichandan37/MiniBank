package com.NewGen.MiniBank.service;


import com.NewGen.MiniBank.dto.AccountRequest;
import com.NewGen.MiniBank.dto.AccountResponse;
import com.NewGen.MiniBank.enums.AccountType;
import com.NewGen.MiniBank.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(AccountRequest accountRequest);

    AccountResponse getAccountById(Long accountId);

    Page<AccountResponse> getAccountsByUser(int userId, Pageable pageable);

    void closeAccount(Long accountId);
}

