package com.example.foodplanner.view;

import com.example.foodplanner.model.enumeration.StarEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class RecipeCardViewModel {

    private Long id;
    private String title;
    private StarEnum stars;

    private String preparation;
    private String image;

    public RecipeCardViewModel() {
    }


    public Long getId() {
        return id;
    }

    public RecipeCardViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPreparation() {
        return preparation;
    }

    public RecipeCardViewModel setPreparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecipeCardViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImage() {
        return image;
    }

    public RecipeCardViewModel setImage(String image) {
        this.image = image;
        return this;
    }



    public StarEnum getStars() {
        return stars;
    }

    public RecipeCardViewModel setStars(StarEnum stars) {
        this.stars = stars;
        return this;
    }




}
