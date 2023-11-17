package com.example.foodplanner.service;


import com.example.foodplanner.model.entity.KitchenProduct;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.enumeration.MealType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecipeService {
	
	public List<Recipe> findAll();
	
	public Recipe findById(Long id);
	
	public void save(Recipe recipe);
	
	public void deleteById(Long id);
	
	public Recipe findByTitle(String title);
	
	public List<Recipe> getRecipesForUserProducts(List<KitchenProduct> products);
	
	public Page<Recipe> getRecipesByPage(int pageId, int pageSize);
	
	public List<String> getNamesLike(String keyword);

	public List<Recipe> findByOwnerIdDesc(Long currentUserId);

	public List<Recipe> getRecipesWaitingForInspection();

	public List<Recipe> getPublicRecipes();

	public void makeRecipePublic(Long recipeId, User publisher);
	
	public void makeRecipePrivate(Long recipeId);

	public List<Recipe> filterRecipesByMealTypesAndSearchProducts(List<Recipe> recipes,
																  List<MealType> selectedMealtypes, List<String> products);

	public Page<Recipe> getPublicRecipes(int pageId, int pageSize);

	public Page<Recipe> findByOwnerId(Long currentUserId, int pageId, int pageSize);

}
