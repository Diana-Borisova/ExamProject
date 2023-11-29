package com.example.foodplanner.controller;


import com.example.foodplanner.model.binding.RecipeCreateBindingModel;
import com.example.foodplanner.model.binding.RecipeEditBindingModel;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.Role;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.model.enumeration.StarEnum;
import com.example.foodplanner.model.sevice.RecipeServiceModel;
import com.example.foodplanner.service.PictureService;
import com.example.foodplanner.service.RecipeService;
import com.example.foodplanner.service.UserService;
import com.example.foodplanner.view.RecipeCardViewModel;
import com.example.foodplanner.view.RecipeDetailsViewModel;
import com.example.foodplanner.view.RecipeEditViewModel;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final ModelMapper modelMapper;
    private final RecipeService recipeService;
    private final PictureService pictureService;

    private final UserService userService;


    public RecipeController(ModelMapper modelMapper, PictureService pictureService, UserService userService, RecipeService recipeService) {
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
        this.recipeService = recipeService;
        this.userService = userService;

    }

    @ModelAttribute("recipeCreateBindingModel")
    public RecipeCreateBindingModel recipeCreateBindingModel() {
        return new RecipeCreateBindingModel();
    }

    @ModelAttribute("recipeEditBindingModel")
    public RecipeEditBindingModel recipeEditBindingModel() {
        return new RecipeEditBindingModel();
    }

    @ModelAttribute("recipeDetailsViewModel")
    public RecipeDetailsViewModel recipeDetailsViewModel(){
        return new RecipeDetailsViewModel();
    }
    @GetMapping("/create")
    public String addRecipe() {
        return "add-recipe";
    }

    
        
    @PostMapping("/create")
    public String addRecipePost(@Valid RecipeCreateBindingModel recipeCreateBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal UserDetails principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeCreateBindingModel",recipeCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeCreateBindingModel", bindingResult);
            return "redirect:/recipes/create";
        }
        if (recipeCreateBindingModel.getImage().isEmpty()){
            recipeCreateBindingModel.setImage(null);
        }
        RecipeServiceModel recipeServiceModel = modelMapper.map(recipeCreateBindingModel, RecipeServiceModel.class);
        setStarEnum(recipeCreateBindingModel.getStars(), recipeServiceModel);
        recipeServiceModel.setRecipeOwner(userService.getUserByEmail(principal.getUsername()));
        recipeServiceModel.getRecipeOwner().setRoles(List.of(userService.getUserRoleByName(RoleEnum.RECIPE_OWNER)));
        userService.getUserById(recipeServiceModel.getRecipeOwner().getId())
                .setRoles(List.of(userService.getUserRoleByName(RoleEnum.RECIPE_OWNER)));
        setShared(recipeCreateBindingModel.isShared(), recipeServiceModel);
        recipeServiceModel.setImage(recipeCreateBindingModel.getImage());
        Long recipeId = recipeService.createRecipe(recipeServiceModel);
        pictureService.uploadRecipeImages(recipeCreateBindingModel.getPictures(), recipeId);

        return "redirect:/";
    }

    @GetMapping("/details/manage/{recipeId}")
    public String manageRecipes(Model model, @PathVariable Long recipeId) {
        RecipeEditViewModel recipeEditViewModel = modelMapper.map(recipeService.getRecipeById(recipeId), RecipeEditViewModel.class);
        model.addAttribute("recipe", recipeEditViewModel);
        return "manage-recipes";
    }

    @GetMapping("/edit/{id}")
    private String editRecipe(RecipeEditViewModel recipeEditViewModel, Model model, @PathVariable Long id) {
        RecipeEditViewModel recipe = modelMapper.map(recipeService.getRecipeById(id), RecipeEditViewModel.class);
        recipeEditViewModel.setImageUrls(pictureService.getPicturesByRecipeId(id));
        model.addAttribute("recipe", recipe);
        return "edit-recipe";
    }

    @PatchMapping("/edit/{recipeId}")
    public String editRecipePatch(@Valid RecipeCreateBindingModel recipeCreateBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                @PathVariable Long recipeId) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeCreateBindingModel", recipeCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeCreateBindingModel", bindingResult);
            return "redirect:/users/edit-recipe/" + recipeId;
        }
        RecipeServiceModel recipe = modelMapper.map(recipeCreateBindingModel, RecipeServiceModel.class);
        recipe.setId(recipeId);
        recipe.setShared(recipeCreateBindingModel.isShared());
        recipe.setDescription(recipeCreateBindingModel.getDescription());
        setStarEnum(recipeCreateBindingModel.getStars(), recipe);
        recipe.setImage(recipeCreateBindingModel.getImage());
        recipe.setRecipeOwner(userService.getCurrentUser());
        pictureService.uploadRecipeImages(recipeCreateBindingModel.getPictures(), recipeId);
      // recipe.setPictures(recipeCreateBindingModel.getPictures());
         recipeService.patchChanges(recipe);
        return "redirect:/details/manage/" +recipeId;
    }
//
//    @GetMapping("/add-room/{id}")
//    public String addRoom(@PathVariable String id, Model model) {
//        model.addAttribute("hotelId", id);
//        return "add-room";
//    }
//
//
//    @PostMapping("/add-room/{hotelId}")
//    public String addRoomPost(@Valid RoomAddBindingModel roomAddBindingModel,
//                              BindingResult bindingResult,
//                              RedirectAttributes redirectAttributes,
//                              @PathVariable Long hotelId) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("roomAddBindingModel", roomAddBindingModel);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.roomAddBindingModel", bindingResult);
//            return "redirect:/hotels/add-room/" + hotelId;
//        }
//        RoomServiceModel roomServiceModel = modelMapper.map(roomAddBindingModel, RoomServiceModel.class).
//                setHotel(hotelService.getHotelById(hotelId));
//        roomService.createRoom(roomServiceModel);
//
//
//        return "redirect:/hotels/rooms/manage/" + hotelId;
//    }


    @GetMapping("/details/{id}")
    public String detailsGet(@PathVariable Long id,
                             Model model,
                             @AuthenticationPrincipal UserDetails userDetails) {
        Recipe recipe = recipeService.getRecipeById(id);
        RecipeDetailsViewModel recipeDetailsViewModel = modelMapper.map(recipe, RecipeDetailsViewModel.class);
        List<String> pictureUrls = pictureService.getPicturesByRecipeId(id);
        recipeDetailsViewModel.setImage(pictureUrls.remove(0));
        recipeDetailsViewModel.setPictures(pictureUrls);
        if (recipe.getRecipeOwner().getId() != null){
            model.addAttribute("isOwner", userService.getUserByEmail(userDetails.getUsername()).getId().equals(recipe.getRecipeOwner().getId()));

        }

       model.addAttribute("recipe", recipeDetailsViewModel);
        model.addAttribute("isAdmin", userService.getCurrentUser().getId() == 1);

//        model.addAttribute("foods", foodService.getHotelsRooms(id).
//                stream().
//                map(r -> modelMapper.map(r, RoomReserveViewModel.class)).
//                collect(Collectors.toList()));
        return "recipe-details";
    }

//    @PostMapping("/details/{id}")
//    public String reservePost(@PathVariable Long id, Model model) {
//        RecipeDetailsViewModel recipeDetailsViewModel = modelMapper.map(recipeService.getRecipeById(id), RecipeDetailsViewModel.class);
//
//        model.addAttribute("recipeData", recipeDetailsViewModel);
//
//        return "redirect:/recipes/owned/" + id;
//    }

//    @GetMapping("/details/{id}")
//    public String getDetails() {
//        return "recipe-details";
//    }
    @GetMapping("/edit-recipe/{id}")
    public String editRecipe(@PathVariable Long id, Model model) {
        RecipeEditViewModel recipeEditViewModel = modelMapper.map(recipeService.getRecipeById(id), RecipeEditViewModel.class);
        recipeEditViewModel.setPictures(pictureService.getPicturesByRecipeId(id));
        model.addAttribute("recipeData", recipeEditViewModel);
        return "edit-recipe";
    }

    @PatchMapping("/edit-recipe/{id}")
    public String editRecipePost(@Valid RecipeEditBindingModel recipeEditBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                @PathVariable Long id) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeEditBindingModel", recipeEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeEditBindingModel", bindingResult);
            return "redirect:/recipes/edit/" + id;
        }

        if (recipeEditBindingModel.getPictures().size()==0){
            recipeEditBindingModel.setImage(null);
        }
        RecipeServiceModel recipeServiceModel = modelMapper.map(recipeEditBindingModel, RecipeServiceModel.class).
                setId(id);
        setStarEnum(recipeEditBindingModel.getStars(), recipeServiceModel);

        if (!Objects.equals(recipeEditBindingModel.getPictures().get(0).getOriginalFilename(), "")) {
            pictureService.uploadRecipeImages(recipeEditBindingModel.getPictures(), id);
        }
        recipeService.saveChanges(recipeServiceModel);

        return "redirect:/recipes/details/" + id;
    }


    @GetMapping("/owned")
    public String ownedRecipes() {

        return "my-recipes";
    }

//    @PostMapping("/api/owned")
//    public String ownedRecipes(
//            @PathVariable Long id,
//            Model model,
//            @AuthenticationPrincipal UserDetails principal,
//            RedirectAttributes redirectAttributes) {
//
//        List<RecipeCardViewModel> recipeCardViewModels = recipeService
//                .getRecipesByRecipeOwnerEmail(principal.getUsername())
//                .stream()
//                .map(recipe -> {
//                    RecipeCardViewModel model1 = modelMapper.map(recipe, RecipeCardViewModel.class);
//                    model1.setImage(pictureService.getPicturesByRecipeId(recipe.getId()).get(0));
//                    return model1;
//                })
//                .collect(Collectors.toList());
//
//        redirectAttributes.addFlashAttribute("myRecipes", recipeCardViewModels);
//        return "redirect:/recipes/owned-all";
//    }

//

    private void setStarEnum(String stars, RecipeServiceModel recipeServiceModel) {
        Arrays.stream(StarEnum.values()).forEach(v -> {
            if (stars.equals(v.toString())) {
                recipeServiceModel.setStars(v);
            }
        });
    }

    private void setShared(boolean shared, RecipeServiceModel recipeServiceModel) {
        recipeServiceModel.setShared(shared);
    }


//    @ModelAttribute("foodBindingModel")
//    public RoomAddBindingModel roomAddBindingModel() {
//        return new RoomAddBindingModel();
//    }

//    @ModelAttribute("reservationCreateBindingModel")
//    public ReservationCreateBindingModel reservationCreateBindingModel() {
//        return new ReservationCreateBindingModel();
//    }
}


