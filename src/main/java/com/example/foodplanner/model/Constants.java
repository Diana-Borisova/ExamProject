package com.example.foodplanner.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

public class Constants {
    public static final String DEFAULT_PROFILE_PICTURE = "http://res.cloudinary.com/dmd3mttpc/image/upload/v1702011701/uvtkvd0xdloxsjfq569g.png";

    public static final MultipartFile DEFAULT_RECIPE_PICTURE = (MultipartFile) Path.of("https://w7.pngwing.com/pngs/157/371/png-transparent-fast-food-junk-food-signage-symbol-food-food-text-logo-thumbnail.png");
}
