package com.example.foodplanner.service.impl;


import com.example.foodplanner.model.entity.Picture;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.repository.PictureRepository;
import com.example.foodplanner.service.CloudinaryService;
import com.example.foodplanner.service.PictureService;
import com.example.foodplanner.service.RecipeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;
    private final RecipeService recipeService;

    public PictureServiceImpl(CloudinaryService cloudinaryService, PictureRepository pictureRepository, RecipeService recipeService) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
        this.recipeService = recipeService;

    }

    @Override
    public void uploadRecipeImages(List<MultipartFile> pictures, Long hotelId) {

        Recipe recipe = recipeService.getRecipeById(hotelId);
        Collections.reverse(pictures);
        pictures.forEach(p -> {
            String url = null;
            try {
                url = cloudinaryService.uploadImage(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
            pictureRepository.save(new Picture().setUrl(url).setRecipe(recipe));

        });
    }

    @Override
    public List<String> getPicturesByRecipeId(Long id) {
        return pictureRepository.getPicturesByRecipeId(id);
    }

    @Override
    public void deleteByUrl(String url) throws IOException {
        pictureRepository.delete(pictureRepository.findPictureByUrl(url).
                orElseThrow(() -> new EntityNotFoundException("Picture")));
        cloudinaryService.deleteByUrl(url);
    }

}
