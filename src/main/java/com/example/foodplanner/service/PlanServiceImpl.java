package com.example.foodplanner.service;


import com.example.foodplanner.model.entity.*;
import com.example.foodplanner.repository.*;
import com.example.foodplanner.model.dto.ShoppingItemDTO;

import com.example.foodplanner.model.enumeration.FoodType;
import com.example.foodplanner.model.enumeration.MealType;
import com.example.foodplanner.model.enumeration.UnitType;
import com.example.foodplanner.exception.DuplicateDishInMealException;
import com.example.foodplanner.exception.OverlappingPlanDatesExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanRepository planRepository;

	@Autowired
	MealRepository mealRepository;

	@Autowired
	ShoppingRepository shoppingRepository;

	@Autowired
	FoodProductRepository foodProductRepository;

	@Autowired
	SingleDishProductRepository singleDishProductRepository;
	
	@Autowired
	DishRepository dishRepository;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Plan getCurrentPlan(User user) {
		return planRepository.findByUserIdAndStartDateBeforeAndEndDateAfter(user, LocalDate.now());
	}

	@Override
	public void save(Plan plan) {
		if (checkIfDatesAreAvailable(plan)) {
			planRepository.save(plan);
		} else {
			throw new OverlappingPlanDatesExceptions(plan.getStartDate(), plan.getEndDate());
		}

	}

	private boolean checkIfDatesAreAvailable(Plan plan) {
		List<Plan> upcomingPlans = getAllUserPlansFrom(plan.getUser().getId(), LocalDate.now());
		List<LocalDate> unavailableDates = upcomingPlans.stream().map(p -> p.getDates()).collect(Collectors.toList())
				.stream().flatMap(List::stream).collect(Collectors.toList());
		if (Collections.disjoint(unavailableDates, plan.getDates())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Plan> getAllUserPlans(Long userId) {
		return planRepository.findAllByUserId(userId);
	}

	@Override
	public List<Plan> getAllUserPlansFrom(Long userId, LocalDate date) {
		return null;
	}

	@Override
	public List<Plan> getAllUserPlansUntil(Long userId, LocalDate date) {
		return null;
	}

	@Override
	public Plan getPlanById(Long id) {
		return planRepository.findById(id).get();
	}

	@Transactional
	@Override
	public void addDishToMeal(Meal meal, Dish dish, int servings) {
		if (meal.hasDish(dish)) {
			throw new DuplicateDishInMealException(dish, meal);
		}
		meal.addDish(dish, servings);
		mealRepository.save(meal);
		addDishIngredientsToShoppingList(meal, dish, servings);
	}

	@Transactional
	@Override
	public void removeDishFromMeal(Meal meal, Dish dish) {
		meal.removeDish(dish);
		mealRepository.save(meal);
	}

	@Override
	public Plan getPlanByMealId(Long mealId) {
		Meal meal = mealRepository.findById(mealId).get();
		return meal.getPlan();
	}



	@Override
	public Meal getMeal(Long id) {
		return mealRepository.findById(id).get();
	}

	@Override
	public Meal getMeal(Long planId, LocalDate date, MealType mealType) {
		return mealRepository.findByPlanIdAndDateAndMealType(planId, date, mealType);
	}

	@Override
	public void saveSingleDish(SingleDishProduct singleDish) {
		singleDishProductRepository.save(singleDish);
	}

	@Override
	public SingleDishProduct getSingleDishProduct(Long id) {
		return singleDishProductRepository.findById(id).get();
	}

	@Override
	public void deleteSingleDish(SingleDishProduct singleDishProduct) {
		//using dish repository because of orphanRemovals=true for ingredients in Dish(parent) class
		//so ingredients would be also removed when deleting dish and avoid: Hibernate AssertionFailure: collection owner not associated with session..
		dishRepository.delete(singleDishProduct);
	};

	@Override
	public void copyPlanMealsToPlan(Plan planWithMeals, Plan newPlan) {
		long i = 0;
		LocalDate newDate;
		List<Meal> newMeals = new ArrayList<Meal>();
		for (LocalDate oldDate : planWithMeals.getDates()) {
			List<Meal> meals = planWithMeals.getMealsForDate(oldDate);
			newDate = newPlan.getStartDate().plusDays(i);
			if (newDate.isAfter(newPlan.getEndDate()))
				break;
			for (Meal meal : meals) {
				Meal newMeal = new Meal(meal);
				newMeal.setDate(newDate);
				newMeal.setPlan(newPlan);
				newMeals.add(newMeal);
			}
			i++;
		}
		newPlan.setMeals(newMeals);
		addIngredientsToShoppingListForNewPlan(newPlan);
	}

	// --------------SHOPPING LIST-------------

	@Override
	public Map<String, List<ShoppingItemDTO>> getPreparedShoppingList(Long planId) {
		List<ShoppingItem> shoppingItems = shoppingRepository.findAllByPlanId(planId);

		// HashMap for containing shopping items grouped by foodType
		Map<String, List<ShoppingItemDTO>> groupedShoppingList = new LinkedHashMap<String, List<ShoppingItemDTO>>();

		// creating empty lists for each of foodtypes
		for (FoodType foodType : FoodType.values()) {
			groupedShoppingList.put(foodType.label, new ArrayList<ShoppingItemDTO>());
		}

		// convert shoppingItems to shoppingItemsDTO, merge similar items by name and
		// group by foodType
		for (ShoppingItem item : shoppingItems) {
			String name = item.getIngredient().getFoodProduct().getName();
			MealDish mealDish = item.getMealDish();
			// calculate amount by ingredient amount in recipe divided by recipe servings
			// and multiplied by dish servings in a meal
			float ammount = (item.getIngredient().getAmmount() / mealDish.getDish().getServings())
					* mealDish.getServings();
			;
			UnitType units = item.getIngredient().getUnitType();
			boolean isDone = item.isDone();
			FoodType foodType = item.getIngredient().getFoodProduct().getFoodType();

			// getting list for specific foodType
			List<ShoppingItemDTO> shoppingItemsDTO = groupedShoppingList.get(foodType.label);

			// getting object with the similar name and same state
			ShoppingItemDTO itemDTO = shoppingItemsDTO.stream()
					.filter(i -> (i.getName().equals(name) && (i.isDone() == isDone) && (i.getUnitType() == units)))
					.findAny().orElse(null);

			// if similar object exists add ammounts, if not add new object
			if (itemDTO != null)
				itemDTO.setAmmount(itemDTO.getAmmount() + ammount);
			else
				shoppingItemsDTO.add(new ShoppingItemDTO(name, ammount, units, isDone));

			// setting modified list for same specific foodType
			groupedShoppingList.put(foodType.label, shoppingItemsDTO);
		}

		return groupedShoppingList;
	}

	@Override
	public void updateShoppingItems(Long planId, String foodProductName, boolean isDone, UnitType unitType) {
		List<ShoppingItem> shoppingItems = shoppingRepository
				.findAllByPlanIdAndIngredientFoodProductNameAndDoneAndIngredientUnitType(planId, foodProductName,
						isDone, unitType);
		for (ShoppingItem shoppingItem : shoppingItems) {
			if (!shoppingItem.isDone())
				shoppingItem.setDone(true);
			else
				shoppingItem.setDone(false);
		}
		shoppingRepository.saveAll(shoppingItems);
	}

	public void addDishIngredientsToShoppingList(Meal meal, Dish dish, int servings) {
		MealDish mealdish = meal.findMealDish(dish);
		for (Ingredient ingredient : dish.getIngredients()) {
			ShoppingItem shoppingItem = new ShoppingItem( ingredient, false, mealdish, meal.getPlan());
			shoppingRepository.save(shoppingItem);
		}
	}
	
	private void addIngredientsToShoppingListForNewPlan(Plan plan) {
		List<ShoppingItem> shoppingItems = new ArrayList<ShoppingItem>();
		for (Meal meal : plan.getMeals()) {
			for(MealDish mealDish : meal.getMealDishes()) {
				for (Ingredient ingredient : mealDish.getDish().getIngredients()) {
					ShoppingItem shoppingItem = new ShoppingItem( ingredient, false, mealDish, meal.getPlan());
					shoppingItems.add(shoppingItem);
				}			
			}
		}
		plan.setShoppingItems(shoppingItems);
	}

}
