package com.NewGen.MiniBank.serviceImpl;

import com.NewGen.MiniBank.exception.AccountNotFoundException;
import com.NewGen.MiniBank.exception.InvalidTransactionException;
import com.NewGen.MiniBank.model.Account;
import com.NewGen.MiniBank.model.Transaction;
import com.NewGen.MiniBank.repo.AccountRepo;
import com.NewGen.MiniBank.repo.TransactionRepo;
import com.NewGen.MiniBank.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class TransactionServiceImpl  implements TransactionService {


    AccountRepo accountRepo;
    TransactionRepo transactionRepo;

    @Autowired
    public  TransactionServiceImpl(AccountRepo accountRepo,TransactionRepo transactionRepo){
        this.accountRepo=accountRepo;
        this.transactionRepo=transactionRepo;
    }

    @Override
    public void deposit(Long accountId, BigDecimal amount){
        Account account=accountRepo.findById(accountId).orElseThrow(()->new AccountNotFoundException("Account unknown"));
//       account.setBalance(BigDecimal.valueOf(account.getBalance()).add(amount).longValue());
        account.credit(amount);
        transactionRepo.save(Transaction.deposit(account,amount));
    }

    @Override
    public void withdraw(Long accountId, BigDecimal amount){
        Account account=accountRepo.findById(accountId).orElseThrow(()->new AccountNotFoundException("Account unknown"));
//        if(BigDecimal.valueOf(account.getBalance()).compareTo(amount)<0){
//            throw new InvalidTransactionException("Insufficient amount");
//        }
//        account.setBalance(BigDecimal.valueOf(account.getBalance()).subtract(amount).longValue());
        account.debit(amount);
        transactionRepo.save(Transaction.withdraw(account,amount));


    }

    @Override
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount){
        Account fromAccount=accountRepo.getReferenceById(fromAccountId);
        Account toAccount=accountRepo.getReferenceById((toAccountId));
//        if(BigDecimal.valueOf(fromAccount.getBalance()).compareTo(amount)<0){
//            throw new InvalidTransactionException("Insufficient amount");
//        }
//        fromAccount.setBalance(fromAccount.getBalance()-amount.longValue());
//        toAccount.setBalance(toAccount.getBalance()+amount.longValue());
        fromAccount.debit(amount);
        toAccount.credit(amount);
        transactionRepo.save(Transaction.transfer(fromAccount,toAccount,amount));
    }


    @Override
    public Page<Transaction> viewDepositTransactions(int userId, Long accountId, Pageable pageable){
        Page<Transaction> transactionPage=transactionRepo.findByTargetAccountAccountNumber(accountId,pageable);
        return transactionPage;
    }
}
