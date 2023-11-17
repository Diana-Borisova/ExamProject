package com.example.foodplanner.repository;


import com.example.foodplanner.model.entity.FoodProduct;
import com.example.foodplanner.model.enumeration.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodProductRepository extends JpaRepository<FoodProduct, Long>{

	@Query("SELECT name FROM FoodProduct where name like %:keyword%")
	List<String> findByNameContaining(@Param("keyword") String keyword);
	
	FoodProduct findByName(String name);

	List<FoodProduct> findByFoodType(FoodType foodType);
	
//	List<FoodProduct> findByNameContaining(String keyword);
}
