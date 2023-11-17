package com.example.foodplanner.repository;

import com.example.foodplanner.model.entity.Meal;
import com.example.foodplanner.model.enumeration.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface MealRepository extends JpaRepository<Meal, Long>{

	Meal findByPlanIdAndDateAndMealType(Long planId, LocalDate date, MealType mealType);

}
