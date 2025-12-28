package com.NewGen.MiniBank.model;

import com.NewGen.MiniBank.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

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
    private AccountStatus status;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

//    version (@Version)
}
