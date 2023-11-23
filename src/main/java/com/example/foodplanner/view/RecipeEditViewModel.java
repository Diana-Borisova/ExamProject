package com.example.foodplanner.view;

import java.util.List;

public class RecipeEditViewModel {

    private String title;

    private String stars;

    private String description;


    private String preparation;

    private String image;

    private boolean shared;
    private List<String> pictures;


    public RecipeEditViewModel() {
    }

    public List<String> getPictures() {
        return pictures;
    }

    public RecipeEditViewModel setPictures(List<String> pictures) {
        this.pictures = pictures;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeEditViewModel setDescription(String description) {
        this.description = description;
        return this;
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


    public String getPreparation() {
        return preparation;
    }

    public RecipeEditViewModel setPreparation(String preparation) {
        this.preparation = preparation;
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
}
