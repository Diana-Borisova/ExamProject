package com.example.foodplanner.controller;


import com.example.foodplanner.model.binding.RecipeCreateBindingModel;
import com.example.foodplanner.model.binding.RecipeEditBindingModel;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.model.enumeration.StarEnum;
import com.example.foodplanner.model.sevice.RecipeServiceModel;
import com.example.foodplanner.service.*;
import com.example.foodplanner.view.RecipeDetailsViewModel;
import com.example.foodplanner.view.RecipeEditViewModel;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final ModelMapper modelMapper;
    private final RecipeService recipeService;
    private final PictureService pictureService;
    private final CommentService commentService;
    private final UserService userService;




    public RecipeController(ModelMapper modelMapper, PictureService pictureService, UserService userService, RecipeService recipeService, CommentService commentService) {
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
        this.recipeService = recipeService;
        this.userService = userService;


        this.commentService = commentService;
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
    public RecipeDetailsViewModel recipeDetailsViewModel() {
        return new RecipeDetailsViewModel();
    }

    @ModelAttribute("recipeEditViewModel")
    public RecipeEditViewModel recipeEditViewModel() {
        return new RecipeEditViewModel();
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
            redirectAttributes.addFlashAttribute("recipeCreateBindingModel", recipeCreateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeCreateBindingModel", bindingResult);
            return "redirect:/recipes/create";
        }

        RecipeServiceModel recipeServiceModel = modelMapper.map(recipeCreateBindingModel, RecipeServiceModel.class);
        setStarEnum(recipeCreateBindingModel.getStars(), recipeServiceModel);
        recipeServiceModel.setRecipeOwner(userService.getUserByEmail(principal.getUsername()));
        recipeServiceModel.getRecipeOwner().setRoles(List.of(userService.getUserRoleByName(RoleEnum.RECIPE_OWNER)));
        userService.getUserById(recipeServiceModel.getRecipeOwner().getId())
                .setRoles(List.of(userService.getUserRoleByName(RoleEnum.RECIPE_OWNER)));
        setShared(recipeCreateBindingModel.isShared(), recipeServiceModel);
        //   recipeServiceModel.setImage(recipeCreateBindingModel.getImage());
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
        model.addAttribute("recipe", recipeEditViewModel);
        model.addAttribute("recipeData", recipe);
        return "edit-recipe";
    }

    @PatchMapping("/edit/{recipeId}")
    public String editRecipePatch(@Valid RecipeEditBindingModel recipeEditBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  @PathVariable Long recipeId)  {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeEditBindingModel", recipeEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeEditBindingModel", bindingResult);
            return "redirect:/users/edit-recipe/" + recipeId;
        }

        RecipeServiceModel recipe = modelMapper.map(recipeEditBindingModel, RecipeServiceModel.class);
        recipe.setId(recipeId);
        recipe.setShared(recipeEditBindingModel.isShared());
        recipe.setDescription(recipeEditBindingModel.getDescription());
        setStarEnum(recipeEditBindingModel.getStars(), recipe);
//        recipe.setImage(recipeCreateBindingModel.getImage());
        recipe.setRecipeOwner(userService.getCurrentUser());
        pictureService.uploadRecipeImages(recipeEditBindingModel.getPictures(), recipeId);
        recipeService.patchChanges(recipe);
        return "redirect:/details/manage/" + recipeId;
    }


    @GetMapping("/details/{id}")
    public String detailsGet(@PathVariable Long id,
                             Model model,
                             @AuthenticationPrincipal UserDetails userDetails) {
        Recipe recipe = recipeService.getRecipeById(id);
        RecipeDetailsViewModel recipeDetailsViewModel = modelMapper.map(recipe, RecipeDetailsViewModel.class);
        List<String> pictureUrls = pictureService.getPicturesByRecipeId(id);
        recipeDetailsViewModel.setImage(pictureUrls.remove(0));
        recipeDetailsViewModel.setPictures(pictureUrls);
        if (recipe.getRecipeOwner().getId() != null) {
            model.addAttribute("isOwner", userService.getUserByEmail(userDetails.getUsername()).getId().equals(recipe.getRecipeOwner().getId()));

        }

        model.addAttribute("recipe", recipeDetailsViewModel);
        model.addAttribute("isAdmin", userService.getCurrentUser().getId() == 1);

        return "recipe-details";
    }

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
                                 @PathVariable Long id) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeEditBindingModel", recipeEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeEditBindingModel", bindingResult);
            return "redirect:/recipes/edit/" + id;
        }


        RecipeServiceModel recipeServiceModel = modelMapper.map(recipeEditBindingModel, RecipeServiceModel.class)
                        .setId(id);


        setStarEnum(recipeEditBindingModel.getStars(), recipeServiceModel);

        if (!Objects.equals(recipeServiceModel.getPictures().get(0).getOriginalFilename(), "")) {
            pictureService.uploadRecipeImages(recipeEditBindingModel.getPictures(), recipeServiceModel.getId());
        }


        recipeService.saveChanges(recipeServiceModel);

        return "redirect:/recipes/details/" + id;
    }

    @GetMapping("/owned")
    public String ownedRecipes() {
        return "my-recipes";
    }

    @GetMapping("/non-shared")
    public String nonSharedRecipes() {
        return "non-shared-recipes";
    }

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

    @GetMapping("/delete/{id}")
    public String deleteRecipe(Model model, @PathVariable Long id) throws IOException {
        RecipeEditViewModel recipeEditViewModel = modelMapper.map(recipeService.getRecipeById(id), RecipeEditViewModel.class);

        RecipeServiceModel recipeServiceModel = modelMapper.map(recipeEditViewModel, RecipeServiceModel.class);
       List<String> pictures = pictureService.getPicturesByRecipeId(recipeServiceModel.getId());
        if (!pictures.isEmpty()){
            for (int i = 0; i <pictures.size() ; i++) {
                pictureService.deleteByUrl(pictures.get(i));
            }
        }

        if(!commentService.getCommentsByRecipe(recipeService.getRecipeById(id)).isEmpty()){

            commentService.deleteCommentsByRecipe(recipeService.getRecipeById(id));
        }

        recipeService.deleteById(recipeServiceModel.getId());
       model.addAttribute("recipes", recipeEditViewModel);
        return "redirect:/";
    }

}


