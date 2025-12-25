package com.NewGen.MiniBank.model;

import com.NewGen.MiniBank.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private int userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany
            @JoinTable(
                    name="user_role",
                    joinColumns = @JoinColumn(name="userId"),
                    inverseJoinColumns = @JoinColumn(name="roleId")
            )
    private Set<Role> role;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
}
