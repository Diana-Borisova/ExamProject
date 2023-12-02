package com.example.foodplanner.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "pictures")
public class
Picture extends BaseEntity {

    @Column(nullable = false)
    private String url;

    @ManyToOne()
    private Recipe recipe;

    public Picture() {
    }

    public String getUrl() {
        return url;
    }

    public Picture setUrl(String url) {
        this.url = url;
        return this;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Picture setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }
}
