package com.beles.converters;

import com.beles.commands.CategoryCommand;
import com.beles.commands.IngredientCommand;
import com.beles.commands.NotesCommand;
import com.beles.commands.RecipeCommand;
import com.beles.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

    private static final Long ID=1L;
    private static final String DESCRIPTION="ddddddddddddd";
    private static final Integer PREP_TIME=12;
    private static final Integer COOK_TIME=30;
    private static final Integer SERVING=15;
    private static final String SOURCE="SOURCE";
    private static final String URL="URL";
    private static final String DIRECTIONS="DIRECTIONS";
    private static final Difficulty DIFFICULTY = Difficulty.MODERATE;
    private static final Long ID_NOTE = 9L;
    private static final Long ID_ING1 = 11L;
    private static final Long ID_ING2 = 22L;
    private static final Long ID_CAT1 = 111L;
    private static final Long ID_CAT2 = 222L;

    RecipeToRecipeCommand recipeconverter;

    @BeforeEach
    void setUp() {
        recipeconverter=new RecipeToRecipeCommand(new NotesToNotesCommand(),
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUniteOfMeasureCommand()));

    }

    @Test
    void convertTestNull() {
        assertNull(recipeconverter.convert(null));
    }

    @Test
    void convertTestEmpty() {
        assertNotNull(recipeconverter.convert(new Recipe()));
    }

    @Test
    void convert() {
        //Given
        Recipe recipe=new Recipe();
        recipe.setId(ID);
        recipe.setDirections(DIRECTIONS);
        recipe.setUrl(URL);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setServing(SERVING);
        recipe.setSource(SOURCE);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);

        Notes n=new Notes();
        n.setId(ID_NOTE);

        recipe.setNotes(n);

        Ingredient i1=new Ingredient();
        i1.setId(ID_ING1);
        Ingredient i2=new Ingredient();
        i2.setId(ID_ING2);
        recipe.getIngredients().add(i1);
        recipe.getIngredients().add(i2);

        Category cc1=new Category();
        cc1.setId(ID_CAT1);
        Category cc2=new Category();
        cc2.setId(ID_CAT2);

        recipe.getCategories().add(cc1);
        recipe.getCategories().add(cc2);

        //When
        RecipeCommand command=recipeconverter.convert(recipe);

        //Then
        assertNotNull(recipe);
        assertNotNull(command);
        assertEquals(ID,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());
        assertEquals(DIRECTIONS,command.getDirections());
        assertEquals(URL,command.getUrl());
        assertEquals(COOK_TIME,command.getCookTime());
        assertEquals(PREP_TIME,command.getPrepTime());
        assertEquals(SERVING,command.getServing());
        assertEquals(SOURCE,command.getSource());
        assertEquals(DIFFICULTY,command.getDifficulty());
        assertEquals(2,command.getCategories().size());
        assertEquals(2,command.getIngredients().size());

    }
}
