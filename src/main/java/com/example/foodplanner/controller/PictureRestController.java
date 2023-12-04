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

    @DeleteMapping("/picture/delete{id}")
    public ResponseEntity<Picture> deletePicture(@PathVariable Long id) throws IOException {
        pictureService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/picture/delete/{url}")
    public ResponseEntity<Picture> deletePictureUrl(@PathVariable String url) throws IOException {
        pictureService.deleteByUrl(url);
        return ResponseEntity.status(204).build();
    }


//    @DeleteMapping("/picture/delete")
//    public ResponseEntity<Picture> deletePicture(@RequestBody Map<String, String> request) throws IOException {
//        String url = request.get("url");
//        pictureService.deleteByUrl(url);
//        return ResponseEntity.status(204).build();
//    }


}
