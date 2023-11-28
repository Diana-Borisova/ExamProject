package com.example.foodplanner.controller;

import com.example.foodplanner.model.entity.Picture;
import com.example.foodplanner.service.PictureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PictureRestController {
    private final PictureService pictureService;

    public PictureRestController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @DeleteMapping("/picture/delete")
    public ResponseEntity<Picture> deletePicture(@RequestBody String url) throws IOException {
        pictureService.deleteByUrl(url);
        return ResponseEntity.status(204).build();
    }
}
