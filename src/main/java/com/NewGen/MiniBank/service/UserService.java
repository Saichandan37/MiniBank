package com.NewGen.MiniBank.service;

import com.NewGen.MiniBank.dto.UserRequest;
import com.NewGen.MiniBank.dto.UserResponse;
import com.NewGen.MiniBank.enums.RoleName;
import com.NewGen.MiniBank.model.Users;
import org.springframework.http.ResponseEntity;

public interface UserService {

    void createUser(UserRequest user);

    UserResponse getUserById(int userId);

    void assignRole(int userId, RoleName roleName);

    void blockUser(int userId);

    long userCount();
}

