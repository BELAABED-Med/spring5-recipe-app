package com.beles.controllers;

import com.beles.commands.RecipeCommand;
import com.beles.domain.Recipe;
import com.beles.exceptions.NotFoundException;
import com.beles.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.validation.ValidatorAdapter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.Validator;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import java.net.URLEncoder;
@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ValidatorAdapter mockValidator = mock(ValidatorAdapter.class);
        mockMvc=MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .setValidator(mockValidator)
                .build();
    }

    @Test
    void showById() throws Exception {

        Recipe returnrecipe=Recipe.builder().id(1L).build();
        when(recipeService.findById(anyLong())).thenReturn(returnrecipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void getRecipeNotFound() throws Exception {
        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/4/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404Error"));
    }
    @Test
    void getRecipeNumberFormatException() throws Exception {

        mockMvc.perform(get("/recipe/frefef/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400Error"));
    }

    @Test
    void getRecipeFormTest() throws Exception{
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    //Validation  Video 10-Validation????
    @Test
    void postRecipeForm() throws Exception{
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","")
                .param("description","")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));

    }

//    @Test
//    public void testPostNewRecipeFormValidationFail() throws Exception {
//        RecipeCommand command = new RecipeCommand();
//        command.setId(2L);
//
//        when(recipeService.saveRecipeCommand(any())).thenReturn(command);
//
//        mockMvc.perform(post("/recipe")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id", "")
//
//        )
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("recipe"))
//                .andExpect(view().name("recipe/recipeform"));
//    }

    @Test
    void getupdateRecipeForm() throws Exception {
        RecipeCommand command=new RecipeCommand();
        command.setId(3L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/3/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    void TestDeletion() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(view().name("redirect:/"))
                .andExpect(status().is3xxRedirection());

        verify(recipeService,times(1)).deleteRecipe(1L);


    }


}
