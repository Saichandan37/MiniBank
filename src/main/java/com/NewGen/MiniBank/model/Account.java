package com.NewGen.MiniBank.model;

import com.NewGen.MiniBank.enums.AccountStatus;
import com.NewGen.MiniBank.enums.AccountType;
import com.NewGen.MiniBank.exception.InvalidTransactionException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long accountNumber;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status;


    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Version
    private Long version;


    //in cents
    public Account(Long balance,AccountStatus status,AccountType accountType,Users users){
        this.balance=balance;
        this.accountType=accountType;
        this.status=status;
        this.users=users;
    }

//    version (@Version)


    public void debit(BigDecimal amount){
        if(this.balance-amount.longValue()<0){
            throw new InvalidTransactionException("Insufficient Balance");
        }
        this.balance-=amount.longValue();
    }

    public void credit(BigDecimal amount){
        this.balance +=amount.longValue();
    }
}
