package com.beles.services;

import com.beles.commands.RecipeCommand;
import com.beles.converters.RecipeCommandToRecipe;
import com.beles.converters.RecipeToRecipeCommand;
import com.beles.domain.Recipe;
import com.beles.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "some description";
    public static final long ID = 1L;

    @Autowired
    RecipeToRecipeCommand recipeConverter;

    @Autowired
    RecipeCommandToRecipe recipeCommandConverter;

    @Autowired
    RecipeRepository recipeRepository;

    RecipeService recipeService;

    @BeforeEach
    void setUp() {
        recipeService= new RecipeServiceImpl(recipeCommandConverter,recipeConverter,recipeRepository);
    }

    @Transactional
    @Test
    void saveRecipeCommand() {
        //Given
        Iterable<Recipe> recipes =recipeRepository.findAll();
        Recipe testRecipe=recipes.iterator().next();
        RecipeCommand testRecipeCommand=recipeConverter.convert(testRecipe);

        //When
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand=recipeService.saveRecipeCommand(testRecipeCommand);

        //Then

        assertEquals(NEW_DESCRIPTION,savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(),savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(),savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(),savedRecipeCommand.getIngredients().size());

    }

}
