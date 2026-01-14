package com.NewGen.MiniBank.service;

import com.NewGen.MiniBank.dto.UserResponse;
import com.NewGen.MiniBank.exception.UserNotFoundException;
import com.NewGen.MiniBank.model.UserPrincipal;
import com.NewGen.MiniBank.model.Users;
import com.NewGen.MiniBank.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserRepo userRepo;


    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.NewGen.MiniBank.model.Users users =userRepo.findByUsername(username).orElseThrow(()->new UserNotFoundException("User not found"));
        if(users == null){
            System.out.println("UserName not found");
        }

        return new UserPrincipal(users);
    }
}
