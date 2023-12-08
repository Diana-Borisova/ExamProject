package com.example.foodplanner.controller;


import com.example.foodplanner.model.entity.Picture;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.service.PictureService;
import com.example.foodplanner.service.RecipeService;
import com.example.foodplanner.view.RecipeCardViewModel;
import com.example.foodplanner.view.RecipeDetailsViewModel;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeRestController {

    private final RecipeService recipeService;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;


    public RecipeRestController(RecipeService recipeService, PictureService pictureService, ModelMapper modelMapper) {
        this.recipeService = recipeService;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;

    }


    @GetMapping("/all")
    public ResponseEntity<List<RecipeCardViewModel>> getAll() {
        List<RecipeCardViewModel> recipeCardViewModels = recipeService.
                getAllRecipes().
                stream().
                map(r -> {
                    RecipeCardViewModel model = modelMapper.map(r, RecipeCardViewModel.class);
                    model.setImage(pictureService.getPicturesByRecipeId(r.getId()).get(0));
                    return model;
                }).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(recipeCardViewModels);
    }

    @GetMapping("/api/owned")
    public ResponseEntity<List<RecipeCardViewModel>> getOwnedRecipes(@AuthenticationPrincipal UserDetails principal) {
        List<RecipeCardViewModel> recipeCardViewModels = recipeService.
                getRecipesByRecipeOwnerEmail(principal.getUsername()).
                stream().
                map(r -> {
                    RecipeCardViewModel model = modelMapper.map(r, RecipeCardViewModel.class);
                    model.setImage(pictureService.getPicturesByRecipeId(r.getId()).get(0));
                    return model;
                }).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(recipeCardViewModels);
    }

    @GetMapping("/recipes/{userId}")
    public ResponseEntity<List<RecipeDetailsViewModel>> getRecipesByUserId(@PathVariable Long userId) {
        List<RecipeDetailsViewModel> recipes = recipeService.
                getUsersRecipes(userId).
                stream().
                map(r -> modelMapper.map(r, RecipeDetailsViewModel.class)).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(recipes);
    }

//    @GetMapping("/set-cookie")
//    public String setCookie(HttpServletResponse response) {
//        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", "8DEE7DFB6C8063CA5BA8D6E5F188618D")
//                .sameSite("None")
//                .secure(true)
//                .build();
//
//        response.addHeader("Set-Cookie", cookie.toString());
//        return "Cookie set successfully";
//    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Recipe> removeRecipe(@PathVariable Long id) {

        recipeService.deleteById(id);


        return ResponseEntity.status(204).build();
    }




}
