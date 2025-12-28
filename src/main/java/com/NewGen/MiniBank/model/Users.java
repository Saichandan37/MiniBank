package com.NewGen.MiniBank.model;

import com.NewGen.MiniBank.dto.UserRequest;
import com.NewGen.MiniBank.enums.AccountStatus;
import com.NewGen.MiniBank.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
//userID,account_status, password, status, username
//UserRequest,accountStatus,status
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
    private Set<Role> roles;

    @OneToMany(mappedBy = "users")
    private List<Account> accounts;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    public Users(AccountStatus accountStatus, String password,UserStatus status,String username){
        this.accountStatus=accountStatus;
        this.password=password;
        this.status=status;
        this.username=username;
    }

    public Users(UserRequest userRequest,AccountStatus accountStatus,UserStatus userStatus){
        this.username=userRequest.getUserName();
        this.password=userRequest.getPassword();
    }
}
