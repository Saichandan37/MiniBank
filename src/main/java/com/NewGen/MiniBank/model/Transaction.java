package com.NewGen.MiniBank.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private String sourceAccount;

    @Column(nullable = false)
    private String targetAccount;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date timestamp;
}
