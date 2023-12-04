package com.example.foodplanner.service.impl;
import com.example.foodplanner.model.entity.Recipe;
import com.example.foodplanner.service.RecipeService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.example.foodplanner.service.impl.RecipeServiceImpl;
import com.example.foodplanner.model.entity.Picture;
import com.example.foodplanner.service.CloudinaryService;
import com.example.foodplanner.repository.PictureRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PictureServiceImplTest {

    private PictureServiceImpl serviceToTest;
    private Picture picture1, picture2;


    @Mock
    CloudinaryService cloudinaryService;
    @Mock
    PictureRepository pictureRepository;
    @Mock
    RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        String url1 = "/test/url1";
        String url2 = "/test/url2";
        picture1 = new Picture();
        picture1.
                setUrl(url1).
                setId(1L);
        picture2 = new Picture();
        picture2.
                setUrl(url2).
                setId(2L);

        serviceToTest = new PictureServiceImpl(cloudinaryService, pictureRepository, recipeService);
    }

    @Test
    public void testGetPicturesByHotelId() {
        Recipe recipe1 = new Recipe();
        recipe1.setTitle("recipe1").setId(1L);
        Recipe recipe2 = new Recipe();
        recipe1.setTitle("recipe2").setId(2L);

        Picture picture1 = new Picture();
        picture1.setUrl("testUrl1.com").setRecipe(recipe1);
        Picture picture2 = new Picture();
        picture2.setUrl("testUrl2.com").setRecipe(recipe1);
        Picture picture3 = new Picture();
        picture3.setUrl("testUrl3.com").setRecipe(recipe2);

        when(pictureRepository.getPicturesByRecipeId(recipe1.getId())).
                thenReturn(List.of(picture1.getUrl(), picture2.getUrl()));

        //act
        List<String> urls = serviceToTest.getPicturesByRecipeId(recipe1.getId());
        //assert
        assertEquals(2, urls.size());
        assertEquals(picture1.getUrl(), urls.get(0));
        assertEquals(picture2.getUrl(), urls.get(1));
    }

    @Test
    public void testDeleteByUrlThrows() throws IOException {
        String notExistedUrl = "incorrect url";
        when(pictureRepository.findPictureByUrl(notExistedUrl)).
                thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> serviceToTest.deleteByUrl(notExistedUrl));
    }

    @Test
    public void testDeleteByUrl() throws IOException {
        String url = picture1.getUrl();
        when(pictureRepository.findPictureByUrl(url))
                .thenReturn(Optional.of(picture1));

        serviceToTest.deleteByUrl(url);

        Mockito.verify(pictureRepository).delete(picture1);
        Mockito.verify(cloudinaryService).deleteByUrl(url);
    }

}
