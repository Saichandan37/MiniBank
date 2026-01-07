package com.NewGen.MiniBank.mapper;

import com.NewGen.MiniBank.dto.AccountRequest;
import com.NewGen.MiniBank.dto.AccountResponse;
import com.NewGen.MiniBank.model.Account;

public class AccountMapper {
    public AccountResponse toAccountResponse(Account account){
//AccountResponse accountResponse=new AccountResponse(account.)
        AccountResponse accountResponse=new AccountResponse(account.getAccountNumber(),account.getBalance(),account.getStatus(),account.getAccountType(),account.getUsers().getUserId());
        return accountResponse;
    }


}
