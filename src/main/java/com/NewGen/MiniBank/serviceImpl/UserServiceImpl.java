package com.NewGen.MiniBank.serviceImpl;

import com.NewGen.MiniBank.dto.UserRequest;
import com.NewGen.MiniBank.dto.UserResponse;
import com.NewGen.MiniBank.enums.AccountStatus;
import com.NewGen.MiniBank.enums.RoleName;
import com.NewGen.MiniBank.enums.UserStatus;
import com.NewGen.MiniBank.exception.RoleNotFoundException;
import com.NewGen.MiniBank.exception.UserNotFoundException;
import com.NewGen.MiniBank.mapper.UserMapper;
import com.NewGen.MiniBank.model.Account;
import com.NewGen.MiniBank.model.Role;
import com.NewGen.MiniBank.model.Users;
import com.NewGen.MiniBank.repo.RoleRepo;
import com.NewGen.MiniBank.repo.UserRepo;
import com.NewGen.MiniBank.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
@Lazy
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserMapper convert;

    @Override
    public long userCount(){
        return userRepo.count();
    }

    @Override
    public void createUser(UserRequest userRequest){
        Role role=roleRepo.findByRoleName(userRequest.getRoleName()).orElseThrow(()->new RoleNotFoundException("Unknown role"));

        Set<Role> roles=Set.of(role);
        Users user = convert.toEntity(userRequest, roles);
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setStatus(UserStatus.USER_ACTIVE);
        userRepo.save(user);
    }

    @Override
    public UserResponse getUserById(int userId){
               Users user= userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("user not found"));

        return convert.toResponse(user);
    }
//        return userRepo.findById(userId).orElse(new Users( ));

    @Override
    @Transactional
    public void assignRole(int userId, RoleName roleName){
      Users user=userRepo.findById((userId)).orElseThrow(()->new UserNotFoundException("User not found"));
      Role role=roleRepo.findByRoleName((roleName)).orElseThrow(()->new RoleNotFoundException("Role not found"));

      user.getRoles().add(role);
    }


    @Override
    public void blockUser(int userId){
Users user=userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
user.setStatus(UserStatus.USER_BLOCKED);

    }
}
