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
	@Column(name = "image")
	private String image;

	@ManyToOne()
	@JoinColumn(name = "recipe_owner_id")
	private User recipeOwner;

	// requested to be made public so everyone could see
	@Column(name = "shared")
	private boolean shared;


    @ManyToMany(mappedBy = "favoriteRecipes")
    private List<User> favoritedByUsers;
	public Recipe() {

	}

    public List<User> getFavoritedByUsers() {
        return favoritedByUsers;
    }

    public Recipe setFavoritedByUsers(List<User> favoritedByUsers) {
        this.favoritedByUsers = favoritedByUsers;
        return this;
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

    public String getImage() {
        return image;
    }

    public Recipe setImage(String image) {
        this.image = image;
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
    void addPicture(List<String> pictures){
        Picture picture = new Picture();
        pictures.add(picture.getUrl());
    }
}
