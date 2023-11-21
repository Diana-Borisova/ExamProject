package com.example.foodplanner.model.binding;



import com.example.foodplanner.validation.FieldsMatch;
import com.example.foodplanner.validation.IsAdult;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@FieldsMatch(first = "password", second = "confirmPassword", message = "Passwords should match")
public class UserRegisterBindingModel {
    @NotEmpty(message = "Field must be filled")
    @Size(min = 2, max = 20, message = "Length must be between 2 and 20 characters")
    private String firstName;
    @NotEmpty(message = "Field must be filled")
    @Size(min = 2, max = 20, message = "Length must be between 2 and 20 characters")
    private String lastName;
    @NotEmpty(message = "Field must be filled")
    @Email
    private String email;
    @Past
    @IsAdult
    @NotNull(message = "Date must be chosen")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @NotEmpty(message = "Field must be filled")
    @Pattern(regexp = "\\+*[0-9]{10,12}")
    private String phoneNumber;
    @NotEmpty(message = "Field must be filled")
    @Size(min = 5, max = 20, message = "Length must be between 2 and 20 characters")
    private String password;
    @NotEmpty(message = "Field must be filled")
    @Size(min = 5, max = 20, message = "Length must be between 2 and 20 characters")
    private String confirmPassword;


    private String planStyle;
    private boolean recipeOwner;

    public UserRegisterBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegisterBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }



    public LocalDate getBirthDate() {
        return birthDate;
    }

    public UserRegisterBindingModel setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public boolean isRecipeOwner() {
        return recipeOwner;
    }

    public UserRegisterBindingModel setRecipeOwner(boolean recipeOwner) {
        this.recipeOwner =recipeOwner;
        return this;
    }

    public String getPlanStyle() {
        return planStyle;
    }

    public UserRegisterBindingModel setPlanStyle(String planStyle) {
        this.planStyle = planStyle;
        return this;
    }
}
