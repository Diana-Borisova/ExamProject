package com.example.foodplanner.service;



import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.sevice.RecipeServiceModel;

import java.util.List;

public interface RecipeService {
    Long createRecipe(RecipeServiceModel recipeServiceModel);

    Recipe getRecipeById(Long id);

    void saveChanges(RecipeServiceModel recipeServiceModel);

    List<RecipeServiceModel> getAllRecipes();

    List<RecipeServiceModel> getRecipesByRecipeOwnerEmail(String username);
}
