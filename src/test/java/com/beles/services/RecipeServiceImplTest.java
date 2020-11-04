package com.beles.services;

import com.beles.converters.RecipeCommandToRecipe;
import com.beles.converters.RecipeToRecipeCommand;
import com.beles.domain.Recipe;
import com.beles.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    @Mock
    RecipeCommandToRecipe recipeCommandConverter;

    @Mock
    RecipeToRecipeCommand recipeConverter;

    @Mock
    RecipeRepository recipeRepository;

    RecipeService recipeService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl( recipeCommandConverter, recipeConverter,recipeRepository);
    }

    @Test
    void getRecipes() {
        //Given
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        HashSet recipesData=new HashSet();
        recipesData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipesData);
        //When
        Set<Recipe> recipes=recipeService.getRecipes();
        //Then
        assertEquals(recipes.size(),1);
        verify(recipeRepository,times(1)).findAll();

    }

    @Test
    void testgetRecipe() {
        Recipe returnRecipe=new Recipe();
        returnRecipe.setId(2L);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(returnRecipe));

        Recipe recipe=recipeService.findById(10L);

        assertNotNull(recipe,"Null Recipe Returned");
        assertEquals(2L,recipe.getId());
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository,never()).findAll();


    }

    @Test
    void tesDelete() {
        //when
        recipeService.deleteRecipe(1L);

        //then
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }

    @Test
    void findByRecipeIdAndId() {
    }
}
