package com.NewGen.MiniBank.repo;

import com.NewGen.MiniBank.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
public Optional<Users> findByUsername(String userName);
}
