package com.NewGen.MiniBank.concurrency.service;

import com.NewGen.MiniBank.repo.TransactionRepo;
import com.NewGen.MiniBank.serviceImpl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    TransactionServiceImpl transactionService;

    @Mock
    TransactionRepo transactionRepo;

    @Test
    public void withDrawSuccess(){

    }
}
