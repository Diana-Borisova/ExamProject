package com.example.foodplanner.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDate postedOn;
    @ManyToOne
    private User user;
    @ManyToOne
    private Recipe recipe;

    public Comment() {
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Comment setUser(User user) {
        this.user = user;
        return this;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Comment setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    public LocalDate getPostedOn() {
        return postedOn;
    }

    public Comment setPostedOn(LocalDate postedOn) {
        this.postedOn = postedOn;
        return this;
    }
}
