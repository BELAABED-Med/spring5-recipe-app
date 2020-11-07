package com.beles.controllers;

import com.beles.commands.IngredientCommand;
import com.beles.commands.RecipeCommand;
import com.beles.services.IngredientService;
import com.beles.services.RecipeService;
import com.beles.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    @Mock
    IngredientService ingredientService;

    @Mock
    RecipeService recipeService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller=new IngredientController(recipeService,ingredientService, unitOfMeasureService);
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void ingredientList() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService,times(1)).findCommandById(anyLong());
    }

    @Test
    void showIngredient() throws Exception {
        //Given
        IngredientCommand command=new IngredientCommand();
        command.setId(1L);
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(command);
        //When

        //Then
        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

    }


    @Test
    void showIngredientForm() throws Exception {
        //Given
        RecipeCommand recipeCommand=new RecipeCommand();

        IngredientCommand command=new IngredientCommand();
        command.setId(1L);

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(command);
        when(unitOfMeasureService.findAllUom()).thenReturn(new HashSet<>());
        //When


        //Then
        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("uomList"))
                .andExpect(model().attributeExists("ingredient"));

    }

    @Test
    void deleteIngredient() throws Exception {
        mockMvc.perform(get("/recipe/1/ingredient/2/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredients"));
    }
}
