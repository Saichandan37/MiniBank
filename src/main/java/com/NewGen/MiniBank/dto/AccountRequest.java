package com.NewGen.MiniBank.dto;

import com.NewGen.MiniBank.enums.AccountStatus;
import com.NewGen.MiniBank.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AccountRequest {

    private AccountType accountType;
    private int userId;
}
