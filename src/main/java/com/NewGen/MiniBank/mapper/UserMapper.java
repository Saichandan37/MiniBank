package com.NewGen.MiniBank.mapper;


import com.NewGen.MiniBank.dto.UserRequest;
import com.NewGen.MiniBank.dto.UserResponse;
import com.NewGen.MiniBank.enums.RoleName;
import com.NewGen.MiniBank.enums.UserStatus;
import com.NewGen.MiniBank.exception.RoleNotFoundException;
import com.NewGen.MiniBank.model.Role;
import com.NewGen.MiniBank.model.Users;
import com.NewGen.MiniBank.repo.RoleRepo;
import com.NewGen.MiniBank.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public Users toEntity(UserRequest request, Set<Role> roles) {

        Users user = new Users();
        user.setUsername(request.getUserName());
       user.setPassword(request.getPassword());
        user.setRoles(roles);   // roles already fetched
        return user;
    }

    public UserResponse toResponse(Users user) {

        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId()); // populated AFTER save
        response.setUserName(user.getUsername());
        response.setUserStatus(user.getStatus());

        Set<RoleName> roleNames = user.getRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        response.setRoleName(roleNames);

        return response;
    }
}

