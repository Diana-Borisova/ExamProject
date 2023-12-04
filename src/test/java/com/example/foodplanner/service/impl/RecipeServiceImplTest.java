package com.example.foodplanner.service.impl;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.model.entity.Role;
import com.example.foodplanner.model.entity.User;
import com.example.foodplanner.model.enumeration.RoleEnum;
import com.example.foodplanner.model.enumeration.StarEnum;
import com.example.foodplanner.model.sevice.RecipeServiceModel;
import com.example.foodplanner.repository.RecipeRepository;
import com.example.foodplanner.service.UserService;
import com.example.foodplanner.service.impl.RecipeServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {

    RecipeServiceImpl serviceToTest;

    private Recipe recipe;
    private User user;
    private final ModelMapper modelMapper = new ModelMapper();

    RecipeRepository mockRecipeRepository = Mockito.mock(RecipeRepository.class);
    UserService mockUserService = Mockito.mock(UserService.class);
    @BeforeEach
    public void setUp() {

        Role userRole = new Role();
        userRole.setName(RoleEnum.USER);

        Role ownerRole = new Role();
        ownerRole.setName(RoleEnum.RECIPE_OWNER);

        user = new User();
        user.
                setEmail("testEmail@mail.com").
                setPassword("123456").
                setFirstName("Boris").
                setLastName("Petrov").
                setRoles(List.of(ownerRole, userRole));

        recipe = new Recipe();
        recipe.
                setTitle("Spaggetti").
                setRecipeOwner(user).
                setStars(StarEnum.FIVE).
                setProducts("pasta, cheese, cream").
                setDescription("testing...").
                setShared(true).
                setCookingTime(30).
                setId(10L);

        serviceToTest = new RecipeServiceImpl(mockRecipeRepository, mockUserService, new ModelMapper());
    }

    @Test
    public void testGetRecipeById() {

        when(mockRecipeRepository.findById(10L)).thenReturn(Optional.of(recipe));

        Recipe returnedRecipe = serviceToTest.getRecipeById(10L);

        assertEquals(recipe.getId(), returnedRecipe.getId());
        assertEquals(recipe.getTitle(), returnedRecipe.getTitle());
        assertEquals(recipe.getRecipeOwner(),returnedRecipe.getRecipeOwner());
        assertEquals(recipe.getStars(), returnedRecipe.getStars());
        assertEquals(recipe.getDescription(), returnedRecipe.getDescription());
        assertEquals(recipe.getProducts(), returnedRecipe.getProducts());
        assertEquals(recipe.getCookingTime(), returnedRecipe.getCookingTime());
    }

    @Test
    public void testGetHotelsByHotelOwner() {
        when(mockRecipeRepository.getAllByRecipeOwnerEmail(user.getEmail())).thenReturn(List.of(recipe));
        when(mockRecipeRepository.getAllByRecipeOwnerEmail("notExistedEmail")).thenReturn(List.of());

        List<RecipeServiceModel> incorrectEmailServiceModels = serviceToTest.getRecipesByRecipeOwnerEmail("notExistedEmail");
        List<RecipeServiceModel> hotelServiceModels = serviceToTest.getRecipesByRecipeOwnerEmail(user.getEmail());

        assertEquals(0, incorrectEmailServiceModels.size());
        assertEquals(1, hotelServiceModels.size());
        assertEquals(recipe.getId(), hotelServiceModels.get(0).getId());
        assertEquals(recipe.getRecipeOwner().getEmail(), hotelServiceModels.get(0).getRecipeOwner().getEmail());
        assertEquals(recipe.getTitle(), hotelServiceModels.get(0).getTitle());
    }


    @Test
    public void testGetAllHotels() {
        Recipe returnedRecipe = new Recipe();
        returnedRecipe.setTitle("Recipe Num 2").setId(2L);
        when(mockRecipeRepository.findAll()).thenReturn(List.of(recipe, returnedRecipe));

        List<RecipeServiceModel> recipeServiceModels = serviceToTest.getAllRecipes();

        assertEquals(2, recipeServiceModels.size());

        assertEquals(recipe.getId(), recipeServiceModels.get(0).getId());
        assertEquals(recipe.getTitle(), recipeServiceModels.get(0).getTitle());
        assertEquals(recipe.getCookingTime(), recipeServiceModels.get(0).getCookingTime());

        assertEquals(returnedRecipe.getTitle(), recipeServiceModels.get(1).getTitle());
        assertEquals(returnedRecipe.getId(), recipeServiceModels.get(1).getId());
    }

    @Test
    public void testSaveChanges() throws IOException {
        RecipeServiceModel recipeServiceModel = new RecipeServiceModel();
        recipeServiceModel.setId(recipe.getId());
        recipeServiceModel.setTitle("New Title").
                setStars(StarEnum.TWO).
                setProducts("cheese, pasta, salmon").
                setCookingTime(33);
        when(mockRecipeRepository.findById(recipe.getId())).
                thenReturn(Optional.of(recipe));

        serviceToTest.saveChanges(recipeServiceModel);

        assertEquals(recipeServiceModel.getId(), recipe.getId());
        assertEquals(recipeServiceModel.getTitle(), recipe.getTitle());
        assertEquals(recipeServiceModel.getStars(), recipe.getStars());
        assertEquals(recipeServiceModel.getCookingTime(), recipe.getCookingTime());
        assertEquals(recipeServiceModel.getProducts(), recipe.getProducts());
    }

    @Test
    public void testSaveChangesThrow() {
        RecipeServiceModel recipeServiceModel = new RecipeServiceModel();
        //Fake ID
        recipeServiceModel.setId(999L);
        recipeServiceModel.
                setTitle("Updated").
                setStars(StarEnum.FIVE).
                setCookingTime(20).
                setDescription("New Description");
        when(mockRecipeRepository.findById(recipeServiceModel.getId())).
                thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class,()->serviceToTest.saveChanges(recipeServiceModel));
    }

}
