package com.example.foodplanner.model.sevice;


import com.example.foodplanner.model.entity.Picture;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.enumeration.StarEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class RecipeServiceModel {
    private Long id;
    private String title;
    private StarEnum stars;
    private String description;

    private String products;
    private int cookingTime;
    private User recipeOwner;

    private boolean shared;

    private List<MultipartFile> pictures;

    public RecipeServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public RecipeServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecipeServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public StarEnum getStars() {
        return stars;
    }

    public RecipeServiceModel setStars(StarEnum stars) {
        this.stars = stars;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getProducts() {
        return products;
    }

    public RecipeServiceModel setProducts(String products) {
        this.products = products;
        return this;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public RecipeServiceModel setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
        return this;
    }

    public User getRecipeOwner() {
        return recipeOwner;
    }

    public RecipeServiceModel setRecipeOwner(User recipeOwner) {
        this.recipeOwner = recipeOwner;
        return this;
    }

    public boolean isShared() {
        return shared;
    }

    public RecipeServiceModel setShared(boolean shared) {
        this.shared = shared;
        return this;
    }

    public List<MultipartFile> getPictures() {
        return pictures;
    }

    public RecipeServiceModel setPictures(List<MultipartFile> pictures) {
        this.pictures = pictures;
        return this;
    }
}
