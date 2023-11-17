package com.example.foodplanner.model.dto;

import com.example.foodplanner.model.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Collection;

public class UserEditBindingModel {
    @NotEmpty(message = "Field must be filled")
    @Size(min = 2, max = 20, message = "Length must be between 2 and 20 characters")
    private String firstName;
    @NotEmpty(message = "Field must be filled")
    @Size(min = 2, max = 20, message = "Length must be between 2 and 20 characters")
    private String lastName;
    @NotEmpty(message = "Field must be filled")
    @Email
    private String email;
    @NotEmpty(message = "Field must be filled")
    @Pattern(regexp = "\\+*[0-9]{10,12}")
    private String phoneNumber;
    @NotEmpty(message = "Field must be filled")
    private Collection<Role> roles;

    @NotEmpty(message = "Field must be filled")
    private String planStyle;


    public UserEditBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEditBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEditBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    public String getEmail() {
        return email;
    }

    public UserEditBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserEditBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public UserEditBindingModel setRoles(Collection<Role> roles) {
        this.roles = roles;
        return this;
    }

    public String getPlanStyle() {
        return planStyle;
    }

    public UserEditBindingModel setPlanStyle(String planStyle) {
        this.planStyle = planStyle;
        return this;
    }
}
