package com.example.foodplanner;


import com.example.foodplanner.service.UserRoleService;
import com.example.foodplanner.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;

    public DBInit(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;

    }

    @Override
    public void run(String... args) throws Exception {
        userRoleService.populateRoles();
        userService.populateInitialUsers();
    }
}
