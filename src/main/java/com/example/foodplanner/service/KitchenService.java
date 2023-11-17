package com.example.foodplanner.service;



import com.example.foodplanner.model.entity.KitchenProduct;

import java.util.List;

public interface KitchenService {
	
	public List<KitchenProduct> getAllProductsForUser(Long id);
	
	public void addProduct(KitchenProduct product);
	
	public void removeProduct(Long id);

	public void removeKitchenProductByFoodProductId(Long userId, Long id);

}
