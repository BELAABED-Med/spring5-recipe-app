package com.beles.services;

import com.beles.commands.IngredientCommand;
import com.beles.converters.IngredientCommandToIngredient;
import com.beles.converters.IngredientToIngredientCommand;
import com.beles.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.beles.converters.UnitOfMeasureToUniteOfMeasureCommand;
import com.beles.domain.Ingredient;
import com.beles.domain.Recipe;
import com.beles.repositories.RecipeRepository;
import com.beles.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientserviceImlTest {

    private final IngredientToIngredientCommand ingredientConverter;
    private final IngredientCommandToIngredient ingredientCommandConverter;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    //Initialisation of the converter
    public IngredientserviceImlTest() {
        this.ingredientConverter=new IngredientToIngredientCommand(new UnitOfMeasureToUniteOfMeasureCommand());
        this.ingredientCommandConverter=new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());

    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService=new IngredientserviceIml(unitOfMeasureRepository, ingredientConverter, ingredientCommandConverter,recipeRepository);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        //Given
        Recipe recipe=new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1=new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2=new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3=new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        //When
        IngredientCommand ingredientCommand=ingredientService.findByRecipeIdAndIngredientId(1L,3L);

        //Then
        assertNotNull(ingredientCommand);
        assertEquals(3L,ingredientCommand.getId());
        assertEquals(1L,ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}
