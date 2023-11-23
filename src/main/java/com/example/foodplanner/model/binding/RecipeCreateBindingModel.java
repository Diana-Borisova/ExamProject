package com.example.foodplanner.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public class RecipeCreateBindingModel {
    @NotBlank(message = "Field cannot be blank")
    @Size(min = 2,max = 20,message = "Length must be between 2 and 20 characters")
    private String title;
    @NotNull(message = "Field cannot be blank")
    private String stars;
    @NotBlank(message = "Field cannot be blank")
    @Size(min = 15,max = 500,message = "Length must be between 15 and 500 characters")
    private String description;

    @NotBlank(message = "Field cannot be blank")
    @Size(min = 15,max = 1000,message = "Length must be between 15 and 500 characters")
    private String preparation;
    @Size(min = 0,max = 50)
    private String image;

    private boolean shared;

    private List<MultipartFile> pictures;

    public RecipeCreateBindingModel() {
    }

    public String getDescription() {
        return description;
    }

    public RecipeCreateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecipeCreateBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }


    public String getStars() {
        return stars;
    }

    public RecipeCreateBindingModel setStars(String stars) {
        this.stars = stars;
        return this;
    }


    public String getPreparation() {
        return preparation;
    }

    public RecipeCreateBindingModel setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public String getImage() {
        return image;
    }

    public RecipeCreateBindingModel setImage(String image) {
        this.image = image;
        return this;
    }

    public List<MultipartFile> getPictures() {
        return pictures;
    }

    public RecipeCreateBindingModel setPictures(List<MultipartFile> pictures) {
        this.pictures = pictures;
        return this;
    }

    public boolean isShared() {
        return shared;
    }

    public RecipeCreateBindingModel setShared(boolean shared) {
        this.shared = shared;
        return this;
    }
}
