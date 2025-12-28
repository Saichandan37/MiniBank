package com.NewGen.MiniBank.dto;

import com.NewGen.MiniBank.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String password;
    private String userName;
    private RoleName roleName;

    public UserRequest(String userName, String password) {
        this.password = password;
        this.userName = userName;
    }
}
