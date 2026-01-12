package com.NewGen.MiniBank.serviceImpl;

import com.NewGen.MiniBank.dto.AccountRequest;
import com.NewGen.MiniBank.dto.AccountResponse;
import com.NewGen.MiniBank.enums.AccountStatus;
import com.NewGen.MiniBank.enums.AccountType;
import com.NewGen.MiniBank.exception.AccountNotFoundException;
import com.NewGen.MiniBank.exception.UserNotFoundException;
import com.NewGen.MiniBank.mapper.AccountMapper;
import com.NewGen.MiniBank.model.Account;
import com.NewGen.MiniBank.model.Users;
import com.NewGen.MiniBank.repo.AccountRepo;
import com.NewGen.MiniBank.repo.UserRepo;
import com.NewGen.MiniBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private UserRepo userRepo;
    private AccountRepo accountRepo;

    @Autowired
    public AccountServiceImpl(UserRepo userRepo,AccountRepo accountRepo){
        this.userRepo=userRepo;
        this.accountRepo=accountRepo;
    }

    @Override
    public AccountResponse createAccount(AccountRequest accountRequest){

        Users user= userRepo.findById(accountRequest.getUserId()).orElseThrow(()-> new UserNotFoundException("Unknown user"));
        AccountResponse accountResponse=new AccountResponse();
        accountResponse.setAccountStatus(AccountStatus.ACTIVE);
        accountResponse.setAccountType(accountRequest.getAccountType());
        accountResponse.setBalance(0L);
        Account account=new Account(0L,AccountStatus.ACTIVE,accountResponse.getAccountType(),user);
        accountRepo.save(account);
        return accountResponse;
    }

    public AccountResponse getAccountById(Long accountId){
        Account account=accountRepo.findById(accountId).orElseThrow(()->new AccountNotFoundException("Account unknown"));
        AccountResponse accountResponse=new AccountResponse(account.getAccountNumber(),account.getBalance(),account.getStatus(),account.getAccountType(),account.getUsers().getUserId());
    return accountResponse;
    }

    @Override
    public Page<AccountResponse> getAccountsByUser(int userId, Pageable pageable) {
               Page<Account> accountPage= accountRepo.findByUsersUserId(userId,pageable);
        AccountMapper accountMapper =new AccountMapper();
              Page<AccountResponse> accountResponses= accountPage.map((account -> accountMapper.toAccountResponse(account)));
        return accountResponses;
    }

    public  void closeAccount(Long accountId){
        Account account=accountRepo.findById(accountId).orElseThrow(()->new AccountNotFoundException("Account unknown"));
        account.setStatus(AccountStatus.BLOCKED);
    }
}
