package com.NewGen.MiniBank.config;

import com.NewGen.MiniBank.dto.UserRequest;
import com.NewGen.MiniBank.enums.AccountStatus;
import com.NewGen.MiniBank.enums.RoleName;
import com.NewGen.MiniBank.enums.UserStatus;
import com.NewGen.MiniBank.mapper.UserMapper;
import com.NewGen.MiniBank.model.Role;
import com.NewGen.MiniBank.model.Users;
import com.NewGen.MiniBank.repo.RoleRepo;
import com.NewGen.MiniBank.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RoleLoadData implements CommandLineRunner {

    @Autowired
    RoleRepo roleRepo;


    @Override
    public void run(String... args) throws Exception {
        if(roleRepo.count()==0) {
            roleRepo.save(new Role(RoleName.ROLE_USER));
            roleRepo.save(new Role(RoleName.ROLE_MODERATOR));
            roleRepo.save(new Role(RoleName.ROLE_ADMIN));
            roleRepo.save(new Role(RoleName.ROLE_SUPPORT));
        }

    }
}
