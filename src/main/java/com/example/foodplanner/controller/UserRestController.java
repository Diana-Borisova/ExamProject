package com.example.foodplanner.controller;


import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.service.UserService;
import com.example.foodplanner.view.UserRoleViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserRoleViewModel>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/profile-pic")
    public ResponseEntity<String> getProfilePicture(@AuthenticationPrincipal UserDetails principal) {
        String profilePic = userService.getUserByEmail(principal.getUsername()).getProfilePicture();
        return ResponseEntity.ok().body(profilePic);
    }



}
