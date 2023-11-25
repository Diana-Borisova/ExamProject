package com.example.foodplanner.service;


import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.sevice.CommentServiceModel;

import java.util.List;

public interface CommentService {
    void addComment(CommentServiceModel commentServiceModel);

    List<CommentServiceModel> getCommentsByRecipe(Recipe recipe);
}
