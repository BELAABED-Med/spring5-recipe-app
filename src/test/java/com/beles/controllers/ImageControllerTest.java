package com.beles.controllers;

import com.beles.commands.RecipeCommand;
import com.beles.services.ImageService;
import com.beles.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller=new ImageController(imageService,recipeService);
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getImageForm() throws Exception {
        //Given
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //Then
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(status().isOk());
        verify(recipeService,times(1)).findCommandById(anyLong());

    }

    @Test
    void handleImagePost() throws Exception {
        MockMultipartFile mockMultipartFile=new MockMultipartFile("imagefile","test.txt","text/plain",
                "Test Test".getBytes());
        mockMvc.perform(multipart("/recipe/1/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/recipe/1/show"));

        verify(imageService,times(1)).saveImage(anyLong(),any());

    }
}
