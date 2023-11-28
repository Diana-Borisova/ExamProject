package com.example.foodplanner.model.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MealType {
	BREAKFAST("Breakfast"),
	LUNCH("Lunch"),
	DINNER("Dinner"),
	SNACKS("Snacks");
	
	public final String label;
	
	MealType(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getName() {
		return this.name();
	}

}
