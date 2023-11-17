package com.example.foodplanner.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_items")
public class ShoppingItem extends BaseEntity{


	@ManyToOne
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;

	@Column(name = "is_done")
	private boolean done;
	
	@ManyToOne
	@JoinColumn(name = "meal_dish_id")
	private MealDish mealDish;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "plan_id")
	private Plan plan;

}
