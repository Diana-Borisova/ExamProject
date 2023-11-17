package com.example.foodplanner.service;



import com.example.foodplanner.model.dto.ShoppingItemDTO;

import com.example.foodplanner.model.entity.*;
import com.example.foodplanner.model.enumeration.MealType;
import com.example.foodplanner.model.enumeration.UnitType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PlanService {

	Plan getCurrentPlan(User user);

	void save(Plan plan);

	Plan getPlanById(Long id);
	
	Meal getMeal(Long id);
	
	Meal getMeal(Long planId, LocalDate mealDate, MealType type);

	Plan getPlanByMealId(Long mealId);

	List<Plan> getAllUserPlans(Long userId);

	List<Plan> getAllUserPlansFrom(Long userId, LocalDate date);

	List<Plan> getAllUserPlansUntil(Long userId, LocalDate date);

	public void updateShoppingItems(Long planId, String ingredientName, boolean isDone, UnitType unitType);

	public Map<String, List<ShoppingItemDTO>> getPreparedShoppingList(Long planId);

	void addDishToMeal(Meal meal, Dish dish, int servings);

	void removeDishFromMeal(Meal meal, Dish dish);

	void saveSingleDish(SingleDishProduct singleDish);

	SingleDishProduct getSingleDishProduct(Long id);

	void deleteSingleDish(SingleDishProduct singleDishProduct);

	void copyPlanMealsToPlan(Plan planWithMeals, Plan newPlan);
	
}
