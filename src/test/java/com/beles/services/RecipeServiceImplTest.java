package com.beles.services;

import com.beles.domain.Recipe;
import com.beles.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService=new RecipeServiceImpl(recipeRepository);

    }

    @Test
    void getRecipes() {
        Recipe recipe=new Recipe();
        HashSet recipesData=new HashSet();
        recipesData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes=recipeService.getRecipes();
        assertEquals(recipes.size(),1);
        verify(recipeRepository,times(1)).findAll();

    }

    @Test
    void testgetRecipe() {
        Recipe returnRecipe=Recipe.builder().id(1L).build();
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(returnRecipe));

        Recipe recipe=recipeService.findById(1L);

        assertNotNull(recipe,"Null Recipe Returned");
        assertEquals(1L,recipe.getId());
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository,never()).findAll();


    }
}
