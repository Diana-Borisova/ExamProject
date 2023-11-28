package com.example.foodplanner.service;

import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.repository.RecipeRepository;
import com.example.foodplanner.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FavoriteService {

    List<Recipe> getAllFavoriteRecipes();
}
