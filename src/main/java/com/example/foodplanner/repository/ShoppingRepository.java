package com.example.foodplanner.repository;


import com.example.foodplanner.model.entity.ShoppingItem;
import com.example.foodplanner.model.enumeration.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingRepository extends JpaRepository<ShoppingItem, Long> {

	List<ShoppingItem> findAllByPlanId(Long planId);

	List<ShoppingItem> findAllByPlanIdAndIngredientFoodProductNameAndDoneAndIngredientUnitType(Long planId,
			String foodProductName, boolean isDone, UnitType unitType);

}
