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

	@Column(name = "description")
	private String description;

	@Column(name = "preparation")
	private String preparation;

	@Column(name = "star")
	@Enumerated(EnumType.STRING)
	private StarEnum stars;
	@Column(name = "image")
	private String image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipe_owner_id")
	private User recipeOwner;

	// requested to be made public so everyone could see
	@Column(name = "shared")
	private boolean shared;


	public Recipe() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	public StarEnum getStars() {
		return stars;
	}

	public void setStars(StarEnum stars) {
		this.stars = stars;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public User getRecipeOwner() {
		return recipeOwner;
	}

	public void setRecipeOwner(User recipeOwner) {
		this.recipeOwner = recipeOwner;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}
}
