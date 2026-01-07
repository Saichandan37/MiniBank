package com.NewGen.MiniBank.repo;

import com.NewGen.MiniBank.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
    public Page<Account> findByUsersUserId(int userId, Pageable pageable);
}
