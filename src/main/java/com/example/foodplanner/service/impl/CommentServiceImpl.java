package com.example.foodplanner.service.impl;

import com.example.foodplanner.model.entity.Comment;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.sevice.CommentServiceModel;
import com.example.foodplanner.repository.CommentRepository;
import com.example.foodplanner.service.CommentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addComment(CommentServiceModel commentServiceModel) {
         commentRepository.save(modelMapper.map(commentServiceModel, Comment.class));
    }

    @Override
    public List<CommentServiceModel> getCommentsByRecipe(Recipe recipe) {
        return commentRepository.
                getCommentsByRecipe(recipe).
                stream().
                map(c -> modelMapper.map(c, CommentServiceModel.class)).
                collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void deleteCommentsByRecipe(Recipe recipe) {
        commentRepository.deleteAllByRecipe(recipe);
    }



    @Override
    public void deletePastComments() {
        commentRepository.deleteCommentsByPostedOnBefore(LocalDate.now().minusYears(1));
    }


}
