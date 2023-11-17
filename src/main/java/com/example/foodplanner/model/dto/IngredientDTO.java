package com.example.foodplanner.model.dto;


import com.example.foodplanner.model.entity.FoodProduct;
import com.example.foodplanner.model.enumeration.UnitType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO {

	private int id;

	private float ammount;
	
	private UnitType unitType;

	private FoodProduct foodProduct;

	private int foodProductId;

}
