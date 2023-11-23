package com.example.foodplanner.controller;


import com.example.foodplanner.service.PictureService;
import com.example.foodplanner.service.RecipeService;
import com.example.foodplanner.view.RecipeCardViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                map(h -> {
                    RecipeCardViewModel model = modelMapper.map(h, RecipeCardViewModel.class);
                    model.setImage(pictureService.getPicturesByRecipeId(h.getId()).get(0));
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
                map(h -> {
                    RecipeCardViewModel model = modelMapper.map(h, RecipeCardViewModel.class);
                    model.setImage(pictureService.getPicturesByRecipeId(h.getId()).get(0));
                    return model;
                }).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(recipeCardViewModels);
    }

//    @GetMapping("/rooms/{hotelId}")
//    public ResponseEntity<List<RoomTableViewModel>> getRoomsByHotelId(@PathVariable Long hotelId) {
//        List<RoomTableViewModel> rooms = roomService.
//                getHotelsRooms(hotelId).
//                stream().
//                map(r -> modelMapper.map(r, RoomTableViewModel.class)).
//                collect(Collectors.toList());
//
//        return ResponseEntity.ok().body(rooms);
//    }

}
