package com.example.foodplanner.web;

import com.example.foodplanner.model.entity.Comment;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.model.enumeration.StarEnum;
import com.example.foodplanner.model.exception.EntityNotFoundException;
import com.example.foodplanner.repository.CommentRepository;
import com.example.foodplanner.repository.LogRepository;
import com.example.foodplanner.repository.RecipeRepository;
import com.example.foodplanner.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static com.example.foodplanner.model.enumeration.RoleEnum.ADMIN;
import static com.example.foodplanner.model.enumeration.RoleEnum.RECIPE_OWNER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class RecipeControllerTest {

    @Autowired
    MockMvc mockMvc;

    private Recipe recipe;


    @Test
    public void testCreateRecipeGet() throws Exception {
        mockMvc.perform(get("/recipes/create")).
                andExpect(status().isOk()).
                andExpect(view().name("add-recipe"));
    }



}
