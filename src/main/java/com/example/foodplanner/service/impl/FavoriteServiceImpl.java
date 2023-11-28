package com.example.foodplanner.service.impl;

import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.repository.RecipeRepository;
import com.example.foodplanner.repository.UserRepository;
import com.example.foodplanner.service.FavoriteService;
import com.example.foodplanner.service.RecipeService;
import com.example.foodplanner.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {


    private final UserService userService;


    private final RecipeService recipeService;

    public FavoriteServiceImpl(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }



    public List<Recipe> getAllFavoriteRecipes() {
        List<User> users = userService.findAll();
        List<Recipe> allFavoriteRecipes = new ArrayList<>();

        for (User user : users) {
            allFavoriteRecipes.addAll(user.getFavoriteRecipes());
        }

        return allFavoriteRecipes;
    }
}
