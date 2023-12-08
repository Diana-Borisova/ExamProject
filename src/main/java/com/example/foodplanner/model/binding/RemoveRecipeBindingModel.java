package com.example.foodplanner.model.binding;


import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RemoveRecipeBindingModel {
    @NotNull(message = "Field cannot be blank")
    private Long id;

    public RemoveRecipeBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public RemoveRecipeBindingModel setId(Long id) {
        this.id = id;
        return this;
    }
}
