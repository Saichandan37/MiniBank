package com.NewGen.MiniBank.controller;


import com.NewGen.MiniBank.enums.TxType;
import com.NewGen.MiniBank.model.Transaction;
import com.NewGen.MiniBank.service.AccountService;
import com.NewGen.MiniBank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Component
@RestController
public class TxController {

    private AccountService accountService;
    private TransactionService transactionService;

    @Autowired
    public TxController(AccountService accountService,TransactionService transactionService){
        this.accountService=accountService;
        this.transactionService=transactionService;
    }

    @PostMapping("users/{id}/account/{accountId}/{txType}")
    public void depositTx(@PathVariable("id") int userId, @PathVariable Long accountId, @PathVariable TxType txType, @RequestParam BigDecimal amount){
        transactionService.deposit(accountService.getAccountById(accountId).getAccountNumber(),amount);
    }

    @GetMapping("users/{userId}/account/{accountId}/transaction")
    public ResponseEntity<Page<Transaction>> viewTransactions(@PathVariable int userId, @PathVariable Long accountId,@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size,
                                                              @RequestParam(defaultValue = "createdAt") String sortBy,
                                                              @RequestParam(defaultValue = "DESC") String direction){

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.Direction.valueOf(direction),
                sortBy
        );

        Page<Transaction> transactionPage=transactionService.viewDepositTransactions(userId,accountId,pageable);
        return new ResponseEntity<>(transactionPage, HttpStatus.OK);
    }
}
