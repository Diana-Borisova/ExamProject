package com.example.foodplanner.model.sevice;

import com.example.foodplanner.model.entity.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class UserServiceModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String profession;
    private boolean recipeOwner;
    private MultipartFile profilePicture;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getProfession() {
        return profession;
    }

    public UserServiceModel setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public boolean isRecipeOwner() {
        return recipeOwner;
    }

    public UserServiceModel setRecipeOwner(boolean recipeOwner) {
        this.recipeOwner = recipeOwner;
        return this;
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public UserServiceModel setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

}
