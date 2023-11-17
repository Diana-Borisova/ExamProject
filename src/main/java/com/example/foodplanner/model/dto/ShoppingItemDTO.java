package com.example.foodplanner.model.dto;


import com.example.foodplanner.model.enumeration.UnitType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingItemDTO {

	private String name;

	private float ammount;

	private UnitType unitType;

	private boolean isDone;

	public ShoppingItemDTO(String name, float ammount, UnitType unitType, boolean isDone) {
		this.name = name;
		this.ammount = ammount;
		this.unitType = unitType;
		this.isDone = isDone;
	}

}
