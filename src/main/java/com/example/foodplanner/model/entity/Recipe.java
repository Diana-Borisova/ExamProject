package com.example.foodplanner.model.entity;


import com.example.foodplanner.model.enumeration.StarEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "recipe")
public class Recipe extends BaseEntity {


	@Column(name = "title")
	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "products", columnDefinition = "TEXT")
	private String products;

	@Column(name = "cooking_time")
	private int cookingTime;

	@Column(name = "star")
	@Enumerated(EnumType.STRING)
	private StarEnum stars;

	@ManyToOne()
	@JoinColumn(name = "recipe_owner_id")
	private User recipeOwner;

	@Column(name = "shared")
	private boolean shared;


	public Recipe() {

	}

    public String getTitle() {
        return title;
    }

    public Recipe setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Recipe setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getProducts() {
        return products;
    }

    public Recipe setProducts(String products) {
        this.products = products;
        return this;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public Recipe setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
        return this;
    }

    public StarEnum getStars() {
        return stars;
    }

    public Recipe setStars(StarEnum stars) {
        this.stars = stars;
        return this;
    }

    public User getRecipeOwner() {
        return recipeOwner;
    }

    public Recipe setRecipeOwner(User recipeOwner) {
        this.recipeOwner = recipeOwner;
        return this;
    }

    public boolean isShared() {
        return shared;
    }

    public Recipe setShared(boolean shared) {
        this.shared = shared;
        return this;
    }


}
