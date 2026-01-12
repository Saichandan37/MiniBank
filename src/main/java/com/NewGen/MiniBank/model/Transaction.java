package com.NewGen.MiniBank.model;

import com.NewGen.MiniBank.enums.TxType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private int transactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TxType txType;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name="source_account_id")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name="target_account_id")
    private Account targetAccount;

    @Column(nullable = false)
    private LocalDateTime timestamp;


    public Transaction(TxType txType,BigDecimal amount,Account fromAccount,Account toAccount,LocalDateTime timestamp){
        this.txType=txType;
        this.amount=amount;
        this.sourceAccount=fromAccount;
        this.targetAccount=toAccount;
        this.timestamp=timestamp;
    }

    public static Transaction deposit(Account toAccount,BigDecimal amount){
        return new Transaction(TxType.DEPOSIT,amount,null,toAccount,LocalDateTime.now());
    }

    public static Transaction withdraw(Account fromAccount , BigDecimal amount){
        return new Transaction(TxType.WITHDRAW,amount,fromAccount,null,LocalDateTime.now());
    }

    public static Transaction transfer(Account fromAccount,Account toAccount,BigDecimal amount){
        return  new Transaction((TxType.TRANSFER),amount,fromAccount,toAccount,LocalDateTime.now() );
    }

}
