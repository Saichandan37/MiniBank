package com.NewGen.MiniBank.controller;


import com.NewGen.MiniBank.dto.AccountRequest;
import com.NewGen.MiniBank.dto.AccountResponse;
import com.NewGen.MiniBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping("createAccount")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody  AccountRequest accountRequest){
     AccountResponse accountResponse=   accountService.createAccount(accountRequest);
     return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/accounts")
    public ResponseEntity<Page<AccountResponse>> getAccountsByUser(@PathVariable int userId,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size,
                                                                   @RequestParam(defaultValue = "createdAt") String sortBy,
                                                                   @RequestParam(defaultValue = "DESC") String direction){
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.Direction.valueOf(direction),
                sortBy
        );
    Page<AccountResponse> pageAccountResponse= accountService.getAccountsByUser(userId,pageable);

    return new ResponseEntity<>(pageAccountResponse,HttpStatus.OK);
    }
}
