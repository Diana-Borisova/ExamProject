package com.example.foodplanner.service.impl;

import com.example.foodplanner.model.entity.Comment;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.Role;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.model.enumeration.StarEnum;
import com.example.foodplanner.model.sevice.CommentServiceModel;
import com.example.foodplanner.repository.CommentRepository;
import com.example.foodplanner.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    private CommentServiceImpl serviceToTest;
    private Role roleUser;
    private User mockUser;
    private Recipe mockRecipe;

    @Mock
    CommentRepository mockCommentRepository;

    @BeforeEach
    public void setUp() {
        roleUser = new Role();
        roleUser.setName(RoleEnum.USER);
        Role roleOwner = new Role();
        roleOwner.setName(RoleEnum.RECIPE_OWNER);

        mockUser = new User();
        mockUser.setEmail("test@test");
        mockUser.setFirstName("Diana");
        mockUser.setLastName("Borisova");
        mockUser.setRoles(List.of(roleUser, roleOwner));

        mockRecipe = new Recipe();
        mockRecipe.setTitle("testTitle");
        mockRecipe.setStars(StarEnum.FIVE);
        mockRecipe.setRecipeOwner(mockUser);
        mockRecipe.setDescription("test test test test test");
        mockRecipe.setProducts("test test test test test");
        mockRecipe.setCookingTime(10);
        mockRecipe.setShared(true);


        serviceToTest = new CommentServiceImpl(mockCommentRepository, new ModelMapper());
    }

    @Test
    public void testGetCommentsByRecipe() {
        Role roleUser = new Role();
        roleUser.setName(RoleEnum.USER);
        Role roleOwner = new Role();
        roleOwner.setName(RoleEnum.RECIPE_OWNER);

        User mockUser = new User();
        mockUser.setEmail("test@test");
        mockUser.setFirstName("Peter");
        mockUser.setLastName("Petrov");
        mockUser.setRoles(List.of(roleUser, roleOwner));

        Recipe mockRecipe = new Recipe();
        mockRecipe.setTitle("testTitle");
        mockRecipe.setStars(StarEnum.FIVE);
        mockRecipe.setRecipeOwner(mockUser);
        mockRecipe.setDescription("test test test test test");
        mockRecipe.setProducts("test test test test test");
        mockRecipe.setCookingTime(10);
        mockRecipe.setShared(true);

        Comment mockComment = new Comment();
        mockComment.setRecipe(mockRecipe);
        mockComment.setContent("Testing...Testing...Testing...");
        mockComment.setUser(mockUser);


        when(mockCommentRepository.getCommentsByRecipe(mockRecipe)).
                thenReturn(List.of(mockComment));
        List<CommentServiceModel> commentServiceModels = serviceToTest.getCommentsByRecipe(mockRecipe);

        assertEquals(1, commentServiceModels.size());
        assertEquals(mockComment.getRecipe(), commentServiceModels.get(0).getRecipe());
        assertEquals(mockComment.getContent(), commentServiceModels.get(0).getContent());
        assertEquals(mockComment.getUser().getEmail(), commentServiceModels.get(0).getUser().getEmail());
    }

}
