package com.example.foodplanner.controller;

import com.example.foodplanner.model.binding.RoleChangeBindingModel;
import com.example.foodplanner.model.binding.UserEditBindingModel;
import com.example.foodplanner.model.binding.UserRegisterBindingModel;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.service.UserService;
import com.example.foodplanner.model.sevice.UserServiceModel;
import com.example.foodplanner.view.UserEditViewModel;
import com.example.foodplanner.view.UserProfileViewModel;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute("userEditBindingModel")
    public UserEditBindingModel userEditBindingModel() {
        return new UserEditBindingModel();
    }


    @ModelAttribute("roleChangeBindingModel")
    public RoleChangeBindingModel roleChangeBindingModel() {
        return new RoleChangeBindingModel();
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        if (!model.containsAttribute("usernameOccupied")) {
            model.addAttribute("usernameOccupied", false);
        }
        return "register-user";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/register")
    public String registerUserPost(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        if (userService.usernameExists(userRegisterBindingModel.getEmail())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("usernameOccupied", true);
            return "redirect:/users/register";
        }
        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";
    }

    @PostMapping("/login-error")
    public String loginFail(@ModelAttribute("email") String username,
                            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("notFound", true);
        redirectAttributes.addFlashAttribute("username", username);
        return "redirect:login";
    }



    @GetMapping("/{id}")
    public String userProfile(@PathVariable Long id,
                              Model model,
                              @AuthenticationPrincipal UserDetails principal) {
        User user = userService.getUserById(id);
        UserProfileViewModel userProfileViewModel = modelMapper.map(user, UserProfileViewModel.class);
        userProfileViewModel.
                setRoles(user.getRoles().stream().
                        map(r -> r.getName().toString()).
                        collect(Collectors.toList()));
        model.addAttribute("user", userProfileViewModel);
        model.addAttribute("isOwner", userService.getUserByEmail(principal.getUsername()).getId().equals(user.getId()));
        return "user-profile";
    }

    @GetMapping("/my-profile")
    public String myProfile(@AuthenticationPrincipal UserDetails principal) {
        User user = userService.getUserByEmail(principal.getUsername());
        return "redirect:/users/" + user.getId();
    }

    @GetMapping("/edit-profile")
    public String editProfile(Model model,
                              @AuthenticationPrincipal UserDetails principal) {
        if (!model.containsAttribute("usernameOccupied")) {
            model.addAttribute("usernameOccupied", false);
        }
        model.addAttribute("userData", modelMapper.map(userService.getUserByEmail(principal.getUsername()), UserEditViewModel.class));
        return "edit-user";
    }

    @PatchMapping("/edit-profile")
    public String editProfilePatch(@Valid UserEditBindingModel userEditBindingModel,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes,
                                   @AuthenticationPrincipal UserDetails principal) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userEditBindingModel", userEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditBindingModel", bindingResult);
            return "redirect:edit-profile";
        }
        if (!principal.getUsername().equals(userEditBindingModel.getEmail()) && userService.usernameExists(userEditBindingModel.getEmail())) {
            redirectAttributes.addFlashAttribute("userEditBindingModel", userEditBindingModel);
            redirectAttributes.addFlashAttribute("usernameOccupied", true);
            return "redirect:edit-profile";
        }
        UserServiceModel userServiceModel = modelMapper.map(userEditBindingModel, UserServiceModel.class);
        userServiceModel.setId(userService.getUserByEmail(principal.getUsername()).getId());
        userService.updateUser(userServiceModel);
        return "redirect:/";
    }

    @PatchMapping("/change-roles/{userId}")
    public String saveRoles(RoleChangeBindingModel roleChangeBindingModel, @PathVariable Long userId) {
        List<RoleEnum> roles = new ArrayList<>();
        roles.add(roleChangeBindingModel.getUser());
        roles.add(roleChangeBindingModel.getAdmin());
        roles.add(roleChangeBindingModel.getRecipeOwner());
        roles = roles.
                stream().
                filter(Objects::nonNull).
                collect(Collectors.toList());
        userService.setUserRoles(userId, roles);
        return "redirect:/admin/manage-users";
    }


}


