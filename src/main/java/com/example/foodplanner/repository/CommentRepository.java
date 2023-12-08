package com.example.foodplanner.repository;

import com.example.foodplanner.model.entity.Comment;
import com.example.foodplanner.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> getCommentsByRecipe(Recipe recipe);

    void deleteCommentsByPostedOnBefore(LocalDate date);

    void deleteAllByRecipe(Recipe recipe);
}
