package com.NewGen.MiniBank.config;

import com.NewGen.MiniBank.dto.UserRequest;
import com.NewGen.MiniBank.enums.AccountStatus;
import com.NewGen.MiniBank.enums.RoleName;
import com.NewGen.MiniBank.enums.UserStatus;
import com.NewGen.MiniBank.mapper.UserMapper;
import com.NewGen.MiniBank.model.Role;
import com.NewGen.MiniBank.model.Users;
import com.NewGen.MiniBank.repo.UserRepo;
import com.NewGen.MiniBank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserLoadData implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if(userService.userCount()==1){
            //userRepo.save(new Users(AccountStatus.ACTIVE,"Iphone@14", UserStatus.USER_ACTIVE,"TEST_USER"));
            UserRequest request = new UserRequest();
            request.setUserName("other_user");
            request.setPassword("password@123");
            request.setRoleName(RoleName.ROLE_USER);

            userService.createUser(request);
        }
    }
}
