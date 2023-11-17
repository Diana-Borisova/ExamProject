package com.example.foodplanner.service;


import com.example.foodplanner.repository.KitchenRepository;
import com.example.foodplanner.model.entity.KitchenProduct;
import com.example.foodplanner.exception.UniqueProductConstraintValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class KitchenServiceImpl implements KitchenService {

	@Autowired
	KitchenRepository kitchenRepository;

	@Override
	public List<KitchenProduct> getAllProductsForUser(Long userId) {
		return kitchenRepository.findByUserId(userId);
	}

	@Override
	public void addProduct(KitchenProduct product) {
		try {
			kitchenRepository.save(product);
		} catch (DataIntegrityViolationException e) {
			throw new UniqueProductConstraintValidationException(product.getFoodProduct().getName());
		}
		
	}
	
	@Override
	public void removeProduct(Long id) {
		kitchenRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void removeKitchenProductByFoodProductId(Long userId, Long id) {
		kitchenRepository.deleteByUserIdAndFoodProductId(userId, id);
	}

}
