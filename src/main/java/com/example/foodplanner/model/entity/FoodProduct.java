package com.example.foodplanner.model.entity;


import com.example.foodplanner.model.enumeration.FoodType;
import com.example.foodplanner.model.enumeration.UnitType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "food_products")
public class FoodProduct extends BaseEntity{



	@Column(name = "name")
	private String name;

	@Column(name = "food_type")
	@Enumerated(EnumType.ORDINAL)
	private FoodType foodType;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "id")
	private Nutrition nutrition;

	public FoodProduct(String name, FoodType foodType, Nutrition nutrition) {
		this.name = name;
		this.foodType = foodType;
		this.nutrition = nutrition;
	}
	
	public Nutrition getNutritionPerUnitType(UnitType unitType) {
		return this.getNutrition().getNutritionPerUnitType(unitType);
	}
	
	
	
	

}
