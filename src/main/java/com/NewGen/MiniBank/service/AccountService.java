package com.NewGen.MiniBank.service;


import com.NewGen.MiniBank.model.Account;

import java.util.List;

public interface AccountService {

//    Account createAccount(Long userId, AccountType type);

    Account getAccountById(Long accountId);

    List<Account> getAccountsByUser(Long userId);

    void closeAccount(Long accountId);
}

