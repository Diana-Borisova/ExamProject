package com.example.foodplanner.repository;

;
import com.example.foodplanner.model.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    @Query("SELECT p.url FROM Picture p WHERE p.recipe.id=?1")
    List<String> getPicturesByRecipeId(Long id);


    Optional<Picture> findPictureByUrl(String url);
}
