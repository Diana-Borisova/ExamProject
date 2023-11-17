package com.example.foodplanner.model.dto;

import com.example.foodplanner.model.entity.Nutrition;
import com.example.foodplanner.model.entity.Preparation;
import com.example.foodplanner.model.enumeration.MealType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecipeFormDTO {

	private int id;

	@NotNull(message = "Įveskite recepto pavadinimą")
	@Size(min = 1, message = "Įveskite recepto pavadinimą")
	private String title;

	@NotNull(message = "Recepte turi būti bent vienas ingredientas")
	private List<IngredientDTO> ingredients;

	@NotNull(message = "Recepte turi būti paruošimo instrukcija")
	private List<Preparation> preparations;

	@NotNull(message = "Pasirinkite bent vieną patiekalo tipą")
	@Size(min = 1, message = "Pasirinkite bent vieną patiekalo tipą")
	private List<MealType> mealTypes;

	private String image;

	private MultipartFile imageFile;

	private String owner;

	private String author;

	private String description;

	private boolean shared;

	private boolean inspected;

	private boolean published;

	private int servings;

	private Nutrition nutritionForDish;

}
