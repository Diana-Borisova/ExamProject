package com.example.foodplanner.service;



import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.sevice.RecipeServiceModel;
import com.example.foodplanner.view.RecipeDetailsViewModel;

import java.util.List;

public interface RecipeService {
    Long createRecipe(RecipeServiceModel recipeServiceModel);

    Recipe getRecipeById(Long id);

    void saveChanges(RecipeServiceModel recipeServiceModel);

    List<RecipeServiceModel> getAllRecipes();

    List<RecipeServiceModel> getRecipesByRecipeOwnerEmail(String username);

    List<RecipeServiceModel> getUsersRecipes(Long userId);

    Long patchChanges(RecipeServiceModel recipeServiceModel);

}
