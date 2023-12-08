package com.example.foodplanner.model.sevice;



import com.example.foodplanner.model.entity.Recipe;

import java.math.BigDecimal;

public class RemoveServiceModel {
    private Long id;

    private Recipe recipe;

    public RemoveServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public RemoveServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public RemoveServiceModel setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }
}
