package com.example.foodplanner.repository;


import com.example.foodplanner.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    List<Recipe> getAllByRecipeOwnerEmail(String email);
}
