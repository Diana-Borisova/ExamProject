package com.example.foodplanner.exception;


import com.example.foodplanner.model.entity.Dish;
import com.example.foodplanner.model.entity.Meal;

public class DuplicateDishInMealException extends RuntimeException{

	public DuplicateDishInMealException(Dish dish, Meal meal) {
		super("Recipe/product with title "+ dish.getTitle() +" is already assigned to: "+meal.getDate()+" " + meal.getMealType().getLabel());
	}
}
