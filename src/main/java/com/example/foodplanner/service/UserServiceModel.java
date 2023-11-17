package com.example.foodplanner.service;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class UserServiceModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String phoneNumber;
    private String password;
    private String planStyle;
    private boolean owner;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UserServiceModel setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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





    public boolean isOwner() {
        return owner;
    }

    public UserServiceModel setOwner(boolean isOwner) {
        this.owner = owner;
        return this;
    }

    public String getPlanStyle() {
        return planStyle;
    }

    public UserServiceModel setPlanStyle(String planStyle) {
        this.planStyle = planStyle;
        return this;
    }
}
