package com.NewGen.MiniBank.controller;

import com.NewGen.MiniBank.dto.UserRequest;
import com.NewGen.MiniBank.dto.UserResponse;
import com.NewGen.MiniBank.model.Users;
import com.NewGen.MiniBank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("createUser")
public  void createUser(@RequestBody UserRequest user){
        userService.createUser(user);

    }
    @GetMapping("user/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable  Integer userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }


}
