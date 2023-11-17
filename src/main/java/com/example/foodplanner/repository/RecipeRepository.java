package com.example.foodplanner.repository;

import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.enumeration.MealType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	
	Recipe findByTitle(String title);
	
	//SELECT * FROM recipe WHERE id IN (SELECT DISTINCT recipe_id from recipe_meal_type WHERE meal_type IN (1, 2));

	List<Recipe> getRecipesByMealTypesIn(List<MealType> mealTypes);
	
	List<Recipe> findByOwnerIdOrderByIdDesc(Long id);
	
	List<Recipe> findBySharedAndInspected(boolean b, boolean c);
	
	List<Recipe> findByInspected(boolean b);
	
	List<Recipe> findByShared(boolean b);
	
	List<Recipe> findByPublished(boolean b);

	List<Recipe> findBySharedAndInspectedAndPublished(boolean b, boolean c, boolean d);

	Page<Recipe> findByPublished(boolean b, Pageable pageable);

	Page<Recipe> findByOwnerId(Long currentUserId, Pageable pageable);
}
