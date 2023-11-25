package com.example.foodplanner.controller;


import com.example.foodplanner.model.entity.Comment;
import com.example.foodplanner.model.sevice.CommentServiceModel;
import com.example.foodplanner.service.CommentService;
import com.example.foodplanner.service.RecipeService;
import com.example.foodplanner.service.UserService;
import com.example.foodplanner.view.CommentViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentRestController {

    private final CommentService commentService;
    private final RecipeService recipeService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, RecipeService recipeService, UserService userService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.recipeService = recipeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/recipes/{recipeId}/add-comment")
    public ResponseEntity<Comment> addComment(@PathVariable Long recipeId,
                                              @AuthenticationPrincipal UserDetails principal,
                                              @RequestBody String content) {
        if (content.length() == 0) {
            return ResponseEntity.badRequest().build();
        }
        CommentServiceModel commentServiceModel = new CommentServiceModel();
        commentServiceModel.setContent(content);
        commentServiceModel.setRecipe(recipeService.getRecipeById(recipeId));
        commentServiceModel.setUser(userService.getCurrentUser());
        commentServiceModel.setPostedOn(LocalDate.now());

        commentService.addComment(commentServiceModel);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/recipes/{recipeId}/comments")
    public ResponseEntity<List<CommentViewModel>> getAllComments(@PathVariable Long recipeId) {
        List<CommentViewModel> comments = commentService.
                getCommentsByRecipe(recipeService.getRecipeById(recipeId)).
                stream().
                map(c -> {
                    CommentViewModel commentViewModel = modelMapper.map(c, CommentViewModel.class);
                    commentViewModel.setUserNames(c.getUser().getFirstName() + " " + c.getUser().getLastName());
                    commentViewModel.setUserPic("");
                    commentViewModel.setPostedOn(formatPostedOn(c.getPostedOn()));
                    return commentViewModel;
                }).collect(Collectors.toList());
        return ResponseEntity.ok().body(comments);
    }

    private String formatPostedOn(LocalDate postedOn) {
        int days = Period.between(postedOn,LocalDate.now()).getDays();
        if (days == 0) {
            return "Today";
        }
        return String.format("%d days ago", days);
    }
}
