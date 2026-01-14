package com.NewGen.MiniBank.controller;

import com.NewGen.MiniBank.dto.UserRequest;
import com.NewGen.MiniBank.dto.UserResponse;
import com.NewGen.MiniBank.model.Users;
import com.NewGen.MiniBank.service.UserService;
import com.NewGen.MiniBank.serviceImpl.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
public class UserController {

    UserService userService;
    AuthenticationManager authenticationManager;
    JwtUtility jwtUtility;

    @Autowired
    public UserController(UserService userService,AuthenticationManager authenticationManager,JwtUtility jwtUtility) {
        this.userService = userService;
        this.authenticationManager=authenticationManager;
        this.jwtUtility=jwtUtility;
    }

//    @PostMapping("createUser")
    @PostMapping("/register")
public  void createUser(@RequestBody UserRequest user){
        userService.createUser(user);
    }
    @GetMapping("user/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable  Integer userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }


    @PostMapping("/login")
    public String login(@RequestBody UserRequest userRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPassword())
            );
            return jwtUtility.generateToken(userRequest.getUserName());
        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            return "Invalid Username or Password"; // Password mismatch
        } catch (Exception e) {
            return "Error: " + e.getMessage(); // Other errors (Account locked, etc.)
        }
    }


}
