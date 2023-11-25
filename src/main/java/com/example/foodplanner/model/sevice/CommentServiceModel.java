package com.example.foodplanner.model.sevice;


import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.User;

import java.time.LocalDate;

public class CommentServiceModel {
    private String content;
    private User user;
    private Recipe recipe;
    private LocalDate postedOn;

    public CommentServiceModel() {
    }

    public String getContent() {
        return content;
    }

    public CommentServiceModel setContent(String content) {
        this.content = content;
        return this;
    }

    public User getUser() {
        return user;
    }

    public CommentServiceModel setUser(User user) {
        this.user = user;
        return this;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public CommentServiceModel setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    public LocalDate getPostedOn() {
        return postedOn;
    }

    public CommentServiceModel setPostedOn(LocalDate postedOn) {
        this.postedOn = postedOn;
        return this;
    }
}
