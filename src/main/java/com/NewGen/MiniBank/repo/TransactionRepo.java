package com.NewGen.MiniBank.repo;

import com.NewGen.MiniBank.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
    public Page<Transaction> findByTargetAccountAccountNumber(Long accountNumber, Pageable pageable);
}
