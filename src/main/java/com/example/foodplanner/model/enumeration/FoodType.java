package com.example.foodplanner.model.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FoodType {
	VEGETABLE("Vegetable"),
	FRUIT("Fruit"),
	GRAINS_BEANS_NUTS("Grains, beans, nuts"),
	MEAT_POULTRY("Meat"),
	FISH_SEAFOOD("Fish"),
	DAIRY("Dairy"),
	OTHER("Other");
	
	public final String label;

	
	FoodType(String label) {
		this.label = label;
	}
	
	public String getValue() {
		return this.name();
	}
	
	public String getLabel() {
		return label;
	}
}
