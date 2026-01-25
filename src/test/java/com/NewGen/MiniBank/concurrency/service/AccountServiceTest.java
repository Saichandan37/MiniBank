package com.NewGen.MiniBank.concurrency.service;


import com.NewGen.MiniBank.dto.AccountResponse;
import com.NewGen.MiniBank.mapper.AccountMapper;
import com.NewGen.MiniBank.model.Account;
import com.NewGen.MiniBank.repo.AccountRepo;
import com.NewGen.MiniBank.serviceImpl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepo accountRepo;



    @Test
    public void getAccountByIdSuccess(){

        Account account=new Account();
        when(accountRepo.findById(1L)).thenReturn(Optional.of(account));

        AccountResponse result=accountService.getAccountById(1L);
//        AccountMapper mapper=new AccountMapper();
//
//
//        assertEquals(mapper.toAccountResponse(account),result);
        assertNotNull(result);


        verify(accountRepo.findById(1L));

    }

    @Test
    public void getAccountByIdException(){
        Account account=new Account();
//        when
    }
}
