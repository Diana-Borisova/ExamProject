package com.example.foodplanner.service.impl;


import com.example.foodplanner.model.Constants;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.Role;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.repository.UserRepository;
import com.example.foodplanner.repository.UserRoleRepository;
import com.example.foodplanner.service.CloudinaryService;
import com.example.foodplanner.service.UserService;
import com.example.foodplanner.model.sevice.UserServiceModel;
import com.example.foodplanner.view.UserRoleViewModel;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.cloudinaryService = cloudinaryService;

        this.modelMapper = modelMapper;

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void populateInitialUsers() {
        if (userRepository.count() == 0) {
            User admin = new User().
                    setFirstName("Admin").
                    setLastName("Admin").
                    setEmail("admin@abv.bg").
                    setPassword(passwordEncoder.encode("topsecret")).

                    setRoles(List.of(
                            userRoleRepository.getUserRoleByName(RoleEnum.ADMIN).orElseThrow(() -> new EntityNotFoundException("UserRole")),
                            userRoleRepository.getUserRoleByName(RoleEnum.USER).orElseThrow(() -> new EntityNotFoundException("UserRole")),
                            userRoleRepository.getUserRoleByName(RoleEnum.RECIPE_OWNER).orElseThrow(() -> new EntityNotFoundException("UserRole"))
                    ));
            userRepository.save(admin);
        }
    }
    @Override
    public Long registerUser(UserServiceModel userServiceModel) throws IOException {
        User user = modelMapper.map(userServiceModel, User.class);
        if (userServiceModel.getProfilePicture()!=null && !userServiceModel.getProfilePicture().isEmpty()) {
            user.setProfilePicture(cloudinaryService.
                    uploadImage(userServiceModel.
                            getProfilePicture()));
        } else {
            user.setProfilePicture(Constants.DEFAULT_PROFILE_PICTURE);
        }
        user.setPassword(passwordEncoder.
                encode(userServiceModel.getPassword()));


        if (userServiceModel.isRecipeOwner()) {
            user.setRoles(List.of(userRoleRepository.getUserRoleByName(RoleEnum.USER).
                            orElseThrow(() -> new EntityNotFoundException("UserRole")),
                    userRoleRepository.getUserRoleByName(RoleEnum.RECIPE_OWNER).
                            orElseThrow(() -> new EntityNotFoundException("UserRole"))));
        } else {
            user.setRoles(List.of(userRoleRepository.
                    getUserRoleByName(RoleEnum.USER).
                    orElseThrow(() -> new EntityNotFoundException("UserRole"))));
        }
        return userRepository.save(user).getId();
    }

    @Override
    public boolean usernameExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


    @Override
    public User getUserByEmail(String username) {
        return userRepository.findByEmail(username).
                orElseThrow(() -> new EntityNotFoundException("User"));
    }



    @Override
    public void updateUser(UserServiceModel userServiceModel) throws IOException {
        User user = userRepository.findById(userServiceModel.getId()).
                orElseThrow(() -> new EntityNotFoundException("User"));
        user.setFirstName(userServiceModel.getFirstName());
        user.setLastName(userServiceModel.getLastName());
        user.setEmail(userServiceModel.getEmail());
        user.setPhoneNumber(userServiceModel.getPhoneNumber());
        user.setProfession(userServiceModel.getProfession());
        if (userServiceModel.getProfilePicture() != null) {
            if (!"".equals(userServiceModel.getProfilePicture().getOriginalFilename())) {
                cloudinaryService.deleteByUrl(userServiceModel.getProfilePicture().getOriginalFilename());
                user.setProfilePicture(cloudinaryService.uploadImage(userServiceModel.getProfilePicture()));
            }
        }
        user.setFavoriteRecipes(userServiceModel.getFavoriteRecipes());
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User"));
    }

    @Override
    public List<UserRoleViewModel> getAllUsers() {
        return userRepository.
                findAll().
                stream().
                map(u -> {
                    UserRoleViewModel user = modelMapper.map(u, UserRoleViewModel.class);
                    user.setRoles(u.getRoles().
                            stream().
                            map(r -> r.getName().toString()).
                            collect(Collectors.toList()));
                    return user;
                }).
                collect(Collectors.toList());
    }

    @Override
    public void setUserRoles(Long userId, List<RoleEnum> roles) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User"));
        user.setRoles(roles.
                stream().
                map(re -> userRoleRepository.getUserRoleByName(re).orElseThrow(() -> new EntityNotFoundException("Role")))
                .collect(Collectors.toList()));
        userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByEmail(authentication.getName()).orElseThrow();
    }

    @Override
    public Long getCurrentUserId() {
        User user = getCurrentUser();
        return user.getId();
    }


    @Override
    public boolean hasCurrentUserRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(role));
        return hasUserRole;
    }

    @Override
    public Role getUserRoleByName(RoleEnum roleEnum) {

        return new Role().setName(roleEnum);
    }
    @Override
    public List<Recipe> getFavoriteRecipesForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User"));
        return user.getFavoriteRecipes();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public String getProfilePicture(String username) {
        return this.userRepository.findByEmail(username).get().getProfilePicture();
    }
}
