package com.example.foodplanner.model.binding;


import com.example.foodplanner.model.enumeration.RoleEnum;

public class RoleChangeBindingModel {
    private RoleEnum admin;
    private RoleEnum user;
    private RoleEnum recipeOwner;

    public RoleChangeBindingModel() {
    }


    public RoleEnum getAdmin() {
        return admin;
    }

    public RoleChangeBindingModel setAdmin(RoleEnum admin) {
        this.admin = admin;
        return this;
    }

    public RoleEnum getUser() {
        return user;
    }

    public RoleChangeBindingModel setUser(RoleEnum user) {
        this.user = user;
        return this;
    }

    public RoleEnum getRecipeOwner() {
        return recipeOwner;
    }

    public RoleChangeBindingModel setRecipeOwner(RoleEnum recipeOwner) {
        this.recipeOwner = recipeOwner;
        return this;
    }
}
