package com.example.foodplanner.view;

import java.util.List;

public class RecipeDetailsViewModel {

    private String title;
    private String stars;
    private String description;
    private String products;
    private int cookingTime;
    private String image;
    private boolean shared;
    private List<String> pictures;


    public RecipeDetailsViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public RecipeDetailsViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStars() {
        return stars;
    }

    public RecipeDetailsViewModel setStars(String stars) {
        this.stars = stars;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getProducts() {
        return products;
    }

    public RecipeDetailsViewModel setProducts(String products) {
        this.products = products;
        return this;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public RecipeDetailsViewModel setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
        return this;
    }

    public String getImage() {
        return image;
    }

    public RecipeDetailsViewModel setImage(String image) {
        this.image = image;
        return this;
    }

    public boolean isShared() {
        return shared;
    }

    public RecipeDetailsViewModel setShared(boolean shared) {
        this.shared = shared;
        return this;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public RecipeDetailsViewModel setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }


}
