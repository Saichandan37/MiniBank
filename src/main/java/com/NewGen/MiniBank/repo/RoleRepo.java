package com.NewGen.MiniBank.repo;

import com.NewGen.MiniBank.enums.RoleName;
import com.NewGen.MiniBank.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepo extends JpaRepository<Role,Integer>{
public Optional<Role> findByRoleName(RoleName roleName);
}
