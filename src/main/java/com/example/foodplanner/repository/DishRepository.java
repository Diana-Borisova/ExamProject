package com.example.foodplanner.repository;


import com.example.foodplanner.model.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long>{

}
