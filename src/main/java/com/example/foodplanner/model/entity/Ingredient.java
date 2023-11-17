package com.example.foodplanner.model.entity;
import com.example.foodplanner.model.enumeration.UnitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient extends BaseEntity {



	@Column(name = "ammount")
	private float ammount;
	
	@Column(name="unit_type")
	@Enumerated(EnumType.ORDINAL)
	private UnitType unitType;

	@ManyToOne
	@JoinColumn(name = "food_product_id")
	private FoodProduct foodProduct;

	public Ingredient(Ingredient ingredient) {
		this.ammount = ingredient.ammount;
		this.unitType = ingredient.unitType;
		this.foodProduct = ingredient.foodProduct;
	}

	public Nutrition getNutritionForIngredient() {
		return Nutrition.multiplyNutritionsByFloat(foodProduct.getNutritionPerUnitType(unitType), ammount);
	}

}
