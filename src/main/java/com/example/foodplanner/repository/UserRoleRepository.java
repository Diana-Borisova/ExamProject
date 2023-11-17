package com.example.foodplanner.repository;


import com.example.foodplanner.model.entity.Role;
import com.example.foodplanner.model.enumeration.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getUserRoleByName(RoleEnum name);
    Role findByName(RoleEnum name);

}
