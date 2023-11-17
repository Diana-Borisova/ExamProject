package com.example.foodplanner.service;


import com.example.foodplanner.model.entity.FoodProduct;
import com.example.foodplanner.model.enumeration.FoodType;

import java.util.List;

public interface FoodProductService {
	
	public List<FoodProduct> getFoodProductsByType(FoodType foodType);

	public List<FoodProduct> getFoodProducts();

	public FoodProduct getFoodProduct(Long foodProductId);
	
	public FoodProduct getFoodProduct(String name);
	
	public void addFoodProduct(FoodProduct foodProduct);

	public void deleteFoodProduct(Long id);

}
