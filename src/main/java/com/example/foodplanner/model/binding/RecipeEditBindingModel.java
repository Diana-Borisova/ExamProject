package com.example.foodplanner.model.binding;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public class RecipeEditBindingModel {
    @NotBlank(message = "Field cannot be blank")
    @Size(min = 2,max = 50,message = "Length must be between 2 and 50 characters")
    private String title;
    @NotNull(message = "Field cannot be blank")
    private String stars;
    @NotBlank(message = "Field cannot be blank")
    @Size(min = 15,max = 1000,message = "Length must be between 15 and 1000 characters")
    private String description;

    @NotNull(message = "Field cannot be blank")
    @Positive(message = "Must be positive number")
    @Min(1)
    private int cookingTime;

    @NotBlank(message = "Field cannot be blank")
    @Size(min = 15,max = 1000,message = "Length must be between 15 and 1000 characters")
    private String products;

    private boolean shared;

    private List<MultipartFile> pictures;

    public RecipeEditBindingModel() {
    }

    public String getTitle() {
        return title;
    }

    public RecipeEditBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStars() {
        return stars;
    }

    public RecipeEditBindingModel setStars(String stars) {
        this.stars = stars;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public RecipeEditBindingModel setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
        return this;
    }

    public String getProducts() {
        return products;
    }

    public RecipeEditBindingModel setProducts(String products) {
        this.products = products;
        return this;
    }

    public boolean isShared() {
        return shared;
    }

    public RecipeEditBindingModel setShared(boolean shared) {
        this.shared = shared;
        return this;
    }

    public List<MultipartFile> getPictures() {
        return pictures;
    }

    public RecipeEditBindingModel setPictures(List<MultipartFile> pictures) {
        this.pictures = pictures;
        return this;
    }
}
