package com.NewGen.MiniBank.model;


import com.NewGen.MiniBank.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @ManyToMany(mappedBy = "roles")
    private List<Users> users;

    public Role(RoleName roleName) {
        this.roleName=roleName;
    }
}
