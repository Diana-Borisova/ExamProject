package com.example.foodplanner.service.impl;


import com.example.foodplanner.repository.UserRoleRepository;
import com.example.foodplanner.model.entity.Role;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void populateRoles() {
        if (userRoleRepository.count() == 0) {
            Arrays.stream(RoleEnum.values()).forEach(ur->{
                Role userRole = new Role();
                userRole.setName(ur);
                userRoleRepository.save(userRole);
            });
        }
    }
}
