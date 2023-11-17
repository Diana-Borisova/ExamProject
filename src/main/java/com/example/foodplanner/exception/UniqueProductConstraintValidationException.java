package com.example.foodplanner.exception;

public class UniqueProductConstraintValidationException extends RuntimeException {

	public UniqueProductConstraintValidationException(String name) {
		super("There is already a product with the name: "+ name);
	}
	
}
