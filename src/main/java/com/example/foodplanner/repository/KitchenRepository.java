package com.example.foodplanner.repository;


import com.example.foodplanner.model.entity.KitchenProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KitchenRepository extends JpaRepository<KitchenProduct, Long>{
	
	List<KitchenProduct> findByUserId(Long userId);

	void deleteByUserIdAndFoodProductId(Long userId, Long id);
	
}
