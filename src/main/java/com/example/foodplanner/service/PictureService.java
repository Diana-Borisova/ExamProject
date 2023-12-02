package com.example.foodplanner.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    void uploadRecipeImages(List<MultipartFile> pictures, Long recipeId);

    List<String>  getPicturesByRecipeId(Long id);

    void deleteByUrl(String url) throws IOException;


    void deleteById(Long id);


}
