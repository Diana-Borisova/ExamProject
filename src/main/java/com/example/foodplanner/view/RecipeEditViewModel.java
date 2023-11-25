package com.example.foodplanner.view;

import java.util.List;

public class RecipeEditViewModel {

    private Long id;
    private String title;

    private String stars;

    private String description;

    private String products;
    private int cookingTime;

    private String image;

    private boolean shared;
    private List<String> pictures;


    public RecipeEditViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public RecipeEditViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getStars() {
        return stars;
    }

    public RecipeEditViewModel setStars(String stars) {
        this.stars = stars;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeEditViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getProducts() {
        return products;
    }

    public RecipeEditViewModel setProducts(String products) {
        this.products = products;
        return this;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public RecipeEditViewModel setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
        return this;
    }

    public String getImage() {
        return image;
    }

    public RecipeEditViewModel setImage(String image) {
        this.image = image;
        return this;
    }

    public boolean isShared() {
        return shared;
    }

    public RecipeEditViewModel setShared(boolean shared) {
        this.shared = shared;
        return this;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public RecipeEditViewModel setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RecipeEditViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
