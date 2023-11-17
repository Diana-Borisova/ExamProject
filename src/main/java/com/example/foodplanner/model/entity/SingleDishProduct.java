package com.example.foodplanner.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "single_dish_products")
public class SingleDishProduct extends Dish {


	@Transient
	private final int servings = 1;

	public SingleDishProduct(Dish dish) {
		super();
	}



	@Override
	public String getTitle() {
		return this.getIngredients().get(0).getFoodProduct().getName();
	}

	@Override
	public int getServings() {
		return servings;
	}

	public void setIngredient(Ingredient ingredient) {
		this.setIngredients(Collections.singletonList(ingredient));
	}

}
