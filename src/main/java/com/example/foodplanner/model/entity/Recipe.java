package com.example.foodplanner.model.entity;


import com.example.foodplanner.model.enumeration.MealType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe extends Dish {



	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "recipe_id", nullable = false)
	private List<Preparation> preparations;

	@ElementCollection(targetClass = MealType.class)
	@JoinTable(name = "recipe_meal_Type", joinColumns = @JoinColumn(name = "recipe_id"))
	@Column(name = "meal_type")
	@Enumerated(EnumType.ORDINAL)
	private List<MealType> mealTypes;

	@Column(name = "image")
	private String image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private User owner;

	// requested to be made public so everyone could see
	@Column(name = "shared")
	private boolean shared;

	// approved by admin
	@Column(name = "inspected")
	private boolean inspected;

	// is public
	@Column(name = "published")
	private boolean published;

	@Column(name = "author")
	private String author;

	@Column(name = "servings")
	private int servings;

	public Recipe(Long id, String title) {

		this.title = title;
	}

	public Recipe(Recipe recipe) {
		this.title = recipe.title;
		this.description = recipe.description;
		this.setIngredients(cloneIngredients(recipe.getIngredients()));;
		this.setPreparations(clonePreparations(recipe.getPreparations()));
		this.mealTypes = new ArrayList<MealType>(recipe.mealTypes);
		this.image = recipe.image;
		this.owner = recipe.owner;
		this.shared = recipe.shared;
		this.servings = recipe.servings;
		this.inspected = recipe.inspected;
		this.published = recipe.published;
		this.author = recipe.author;
	}

	private List<Preparation> clonePreparations(List<Preparation> preparations) {
		List<Preparation> copyOfPreparations = new ArrayList<Preparation>();
		for (Preparation preparation : preparations) {
			copyOfPreparations.add(new Preparation(preparation));
		}
		return copyOfPreparations;
	}

}
