package com.example.foodplanner.service.impl;


import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.model.sevice.RecipeServiceModel;
import com.example.foodplanner.repository.RecipeRepository;
import com.example.foodplanner.service.CloudinaryService;
import com.example.foodplanner.service.PictureService;
import com.example.foodplanner.service.RecipeService;
import com.example.foodplanner.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, UserService userService, ModelMapper modelMapper){
        this.recipeRepository = recipeRepository;
        this.userService = userService;
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
    public void saveChanges(RecipeServiceModel recipeServiceModel) throws IOException {
        Recipe recipe = recipeRepository.findById(recipeServiceModel.getId())
                .orElseThrow(() -> new EntityNotFoundException("Recipe"));
        recipe.setTitle(recipeServiceModel.getTitle());
        recipe.setDescription(recipeServiceModel.getDescription());
        recipe.setStars(recipeServiceModel.getStars());

        recipe.setCookingTime(recipeServiceModel.getCookingTime());
        recipe.setShared(recipeServiceModel.isShared());
        recipe.setProducts(recipeServiceModel.getProducts());

        recipeRepository.save(recipe);

    }

    @Override
    public List<RecipeServiceModel> getAllRecipes() {
        return recipeRepository.
                findAll().
                stream().
                map(r -> modelMapper.map(r, RecipeServiceModel.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<RecipeServiceModel> getRecipesByRecipeOwnerEmail(String email) {
        return recipeRepository.
                getAllByRecipeOwnerEmail(email).
                stream().
                map(r -> modelMapper.map(r, RecipeServiceModel.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<RecipeServiceModel> getUsersRecipes(Long userId) {
        return recipeRepository.getRecipesByRecipeOwnerIdOrderByTitle(userId).
                stream().
                map(r -> modelMapper.map(r, RecipeServiceModel.class)).
                collect(Collectors.toList());
    }

    @Override
    public Long patchChanges(RecipeServiceModel recipeServiceModel) {
        Recipe recipe = recipeRepository.findById(recipeServiceModel.getId()).
                orElseThrow(() -> new EntityNotFoundException("Recipe"));
        Long userId = recipe.getRecipeOwner().getId();
       if(recipe.getRecipeOwner() == null){
           recipe.setRecipeOwner(userService.getCurrentUser());
       }
        modelMapper.map(recipeServiceModel, recipe);
        recipe.setRecipeOwner(userService.getUserById(userId));
        recipeRepository.save(recipe);
        return recipe.getRecipeOwner().getId();
    }

    @Override
    public void deleteById(Long recipeId) {
        Recipe recipe = this.recipeRepository.findById(recipeId).
        orElseThrow(() -> new EntityNotFoundException("Recipe"));
        recipe.setRecipeOwner(null);

        this.recipeRepository.delete(recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("Recipe")));

    }

    @Override
    public List<RecipeServiceModel> findAllBySharedIsTrue() {
        return recipeRepository.
                getAllBySharedIsTrue().
                stream().
                map(r -> modelMapper.map(r, RecipeServiceModel.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<RecipeServiceModel> findAllBySharedIsFalse() {
        return recipeRepository.
                getAllBySharedIsFalse().
                stream().
                map(r -> modelMapper.map(r, RecipeServiceModel.class)).
                collect(Collectors.toList());
    }

}
