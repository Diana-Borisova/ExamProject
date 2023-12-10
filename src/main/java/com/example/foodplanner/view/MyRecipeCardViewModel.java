package com.example.foodplanner.view;

import com.example.foodplanner.model.enumeration.StarEnum;

public class MyRecipeCardViewModel {

    private Long id;
    private String title;
    private StarEnum stars;
    private int cookingTime;
    private String image;

    public MyRecipeCardViewModel() {
    }

    public Long getId() {
        return id;
    }

    public MyRecipeCardViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MyRecipeCardViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public StarEnum getStars() {
        return stars;
    }

    public MyRecipeCardViewModel setStars(StarEnum stars) {
        this.stars = stars;
        return this;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public MyRecipeCardViewModel setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
        return this;
    }

    public String getImage() {
        return image;
    }

    public MyRecipeCardViewModel setImage(String image) {
        this.image = image;
        return this;
    }
}
