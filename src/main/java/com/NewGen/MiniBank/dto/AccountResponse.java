package com.NewGen.MiniBank.dto;

import com.NewGen.MiniBank.enums.AccountStatus;
import com.NewGen.MiniBank.enums.AccountType;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountResponse {

    private long accountNumber;
    private long balance;
    private AccountStatus accountStatus;
    private AccountType accountType;
    private int userId;
}
