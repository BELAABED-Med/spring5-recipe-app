package com.beles.services;

import com.beles.domain.Recipe;
import com.beles.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        imageService=new ImageServiceImpl(recipeRepository);
    }

    @Test
    void saveImage() throws IOException {
        MultipartFile multipartFile=new MockMultipartFile("imagefile","test.txt","text/plain","test test".getBytes());
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe=Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        ArgumentCaptor<Recipe> argumentCaptor=ArgumentCaptor.forClass(Recipe.class);

        //when
        imageService.saveImage(1L,multipartFile);
        //Then
        verify(recipeRepository,times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe=argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length,savedRecipe.getImage().length);
    }
}
