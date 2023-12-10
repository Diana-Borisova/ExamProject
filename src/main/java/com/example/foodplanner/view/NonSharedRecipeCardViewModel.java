package com.example.foodplanner.view;

import com.example.foodplanner.model.enumeration.StarEnum;

public class NonSharedRecipeCardViewModel {

    private Long id;
    private String title;
    private StarEnum stars;
    private int cookingTime;
    private String image;

    public NonSharedRecipeCardViewModel() {
    }

    public Long getId() {
        return id;
    }

    public NonSharedRecipeCardViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NonSharedRecipeCardViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public StarEnum getStars() {
        return stars;
    }

    public NonSharedRecipeCardViewModel setStars(StarEnum stars) {
        this.stars = stars;
        return this;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public NonSharedRecipeCardViewModel setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
        return this;
    }

    public String getImage() {
        return image;
    }

    public NonSharedRecipeCardViewModel setImage(String image) {
        this.image = image;
        return this;
    }
}
