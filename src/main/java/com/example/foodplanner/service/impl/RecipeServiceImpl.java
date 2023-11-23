package com.example.foodplanner.service.impl;


import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.sevice.RecipeServiceModel;
import com.example.foodplanner.repository.RecipeRepository;
import com.example.foodplanner.service.RecipeService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ModelMapper modelMapper) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long createRecipe(RecipeServiceModel recipeServiceModel) {
        return recipeRepository.save(modelMapper.map(recipeServiceModel, Recipe.class)).getId();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Recipe"));
    }

    @Override
    public void saveChanges(RecipeServiceModel recipeServiceModel) {
        Recipe recipe = recipeRepository.findById(recipeServiceModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("Recipe"));

        recipe.setTitle(recipeServiceModel.getTitle());
        recipe.setDescription(recipeServiceModel.getDescription());
        recipe.setStars(recipeServiceModel.getStars());
        recipe.setRecipeOwner(recipeServiceModel.getRecipeOwner());
        recipe.setImage(recipeServiceModel.getImage());
        recipe.setPreparation(recipeServiceModel.getPreparation());
        recipe.setShared(recipeServiceModel.isShared());
        recipeRepository.save(recipe);

    }

    @Override
    public List<RecipeServiceModel> getAllRecipes() {
        return recipeRepository.
                findAll().
                stream().
                map(h -> modelMapper.map(h, RecipeServiceModel.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<RecipeServiceModel> getRecipesByRecipeOwnerEmail(String email) {
        return recipeRepository.
                getAllByRecipeOwnerEmail(email).
                stream().
                map(h -> modelMapper.map(h, RecipeServiceModel.class)).
                collect(Collectors.toList());
    }

}
