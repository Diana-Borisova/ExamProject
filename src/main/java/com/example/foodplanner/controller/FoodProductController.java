package com.example.foodplanner.controller;


import com.example.foodplanner.model.entity.FoodProduct;
import com.example.foodplanner.service.FoodProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/foodProduct")
@Slf4j
public class FoodProductController {
	

	private final FoodProductService foodProductService;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	public FoodProductController(FoodProductService foodProductService) {
		this.foodProductService = foodProductService;
	}

	@GetMapping("/list")
	public String displayAllFoodProducts(Model model) {
		List<FoodProduct> foodProducts = foodProductService.getFoodProducts();
		model.addAttribute("foodProducts", foodProducts);
		model.addAttribute("foodProduct", new FoodProduct());
		return "food-products";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addFoodProduct")
	public String addFoodProduct(@ModelAttribute("foodProduct") FoodProduct foodProduct, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			for (Object object : bindingResult.getAllErrors()) {
				FieldError fieldError = (FieldError) object;
				logger.error(fieldError.getCode());
			}
			return "redirect:/foodProduct/list";
		}	
		foodProductService.addFoodProduct(foodProduct);
		return "redirect:/foodProduct/list";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/deleteFoodProduct")
	public String deleteFoodProduct(@RequestParam(name="id") Long id) {
		foodProductService.deleteFoodProduct(id);
		return "redirect:/foodProduct/list";
	}
	
	@GetMapping("/getAll")
	public @ResponseBody List<FoodProduct> getFoodProducts() {
		return foodProductService.getFoodProducts();
	}
	
	@GetMapping("/get")
	public @ResponseBody FoodProduct getFoodProduct(@RequestParam(name="productId") Long id) {
		return foodProductService.getFoodProduct(id);
	}
	
	@GetMapping("/getWithNutrition")
	public @ResponseBody FoodProduct getFoodProductNotLazy(@RequestParam(name="productId") Long id) {
		FoodProduct foodProduct = foodProductService.getFoodProduct(id);
		foodProduct.getNutrition().getKcal();
		return foodProduct;
	}
	
	

}
