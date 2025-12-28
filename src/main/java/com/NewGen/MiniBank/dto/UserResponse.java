package com.NewGen.MiniBank.dto;

import com.NewGen.MiniBank.enums.RoleName;
import com.NewGen.MiniBank.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private int userId;
    private String userName;
    private Set<RoleName> roleName;
    private UserStatus userStatus;

}
