package com.NewGen.MiniBank.service;

import com.NewGen.MiniBank.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface TransactionService {

    void deposit(Long accountId, BigDecimal amount);

    void withdraw(Long accountId, BigDecimal amount);

    void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);

    Page<Transaction> viewDepositTransactions(int userId, Long accountId, Pageable pageable);

}

