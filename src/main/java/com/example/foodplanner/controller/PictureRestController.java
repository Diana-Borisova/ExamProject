package com.example.foodplanner.controller;

import com.example.foodplanner.model.entity.Picture;
import com.example.foodplanner.service.PictureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @DeleteMapping("/picture/delete{id}")
//    public ResponseEntity<Picture> deletePicture(@PathVariable Long id) throws IOException {
//        pictureService.deleteById(id);
//        return ResponseEntity.status(204).build();
//    }
}
