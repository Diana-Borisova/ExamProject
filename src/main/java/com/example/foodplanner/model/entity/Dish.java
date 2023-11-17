package com.example.foodplanner.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "dishes")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Dish extends BaseEntity{



	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dish_id", nullable = false)
	private List<Ingredient> ingredients;

	public Nutrition getNutritionForDish() {
		List<Nutrition> nutritions = ingredients.stream().map(i -> i.getNutritionForIngredient())
				.collect(Collectors.toList());
		return Nutrition.sumNutritions(nutritions);
	}

	public abstract String getTitle();

	public abstract int getServings();
	
	public List<Ingredient> cloneIngredients(List<Ingredient> ingredients) {
		List<Ingredient> copyOfIngredients = new ArrayList<Ingredient>();
		for (Ingredient ingredient : ingredients) {
			copyOfIngredients.add(new Ingredient(ingredient));
		}
		return copyOfIngredients;
	}

}
