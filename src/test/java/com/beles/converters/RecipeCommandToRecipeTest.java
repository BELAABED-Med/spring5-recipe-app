package com.beles.converters;

import com.beles.commands.CategoryCommand;
import com.beles.commands.IngredientCommand;
import com.beles.commands.NotesCommand;
import com.beles.commands.RecipeCommand;
import com.beles.domain.Difficulty;
import com.beles.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

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


    RecipeCommandToRecipe recipeCommandConverter;

    @BeforeEach
    void setUp() {
        recipeCommandConverter= new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()) ,
                new CategoryCommandToCategory(),
                new NotesCommandToNotes()
        );
    }

    @Test
    void convertTestNull(){
        assertNull(recipeCommandConverter.convert(null));
    }

    //this test fails, to be reviewed ASAP !!
    @Test
    void convertTestEmpty(){
        assertNotNull(recipeCommandConverter.convert(new RecipeCommand()));
    }

    @Test
    void convert(){
        //Given
        RecipeCommand command=new RecipeCommand();
        command.setId(ID);
        command.setDirections(DIRECTIONS);
        command.setUrl(URL);
        command.setCookTime(COOK_TIME);
        command.setPrepTime(PREP_TIME);
        command.setServing(SERVING);
        command.setSource(SOURCE);
        command.setDescription(DESCRIPTION);
        command.setDifficulty(DIFFICULTY);

        NotesCommand n=new NotesCommand();
        n.setId(ID_NOTE);

        command.setNotes(n);

        IngredientCommand i1=new IngredientCommand();
        i1.setId(ID_ING1);
        IngredientCommand i2=new IngredientCommand();
        i2.setId(ID_ING2);
        command.getIngredients().add(i1);
        command.getIngredients().add(i2);

        CategoryCommand cc1=new CategoryCommand();
        cc1.setId(ID_CAT1);
        CategoryCommand cc2=new CategoryCommand();
        cc2.setId(ID_CAT2);

        command.getCategories().add(cc1);
        command.getCategories().add(cc2);

        //When
        Recipe recipe=recipeCommandConverter.convert(command);

        //Then
        assertNotNull(recipe);
        assertNotNull(command);

        assertEquals(ID,recipe.getId());
        assertEquals(DESCRIPTION,recipe.getDescription());
        assertEquals(DIRECTIONS,recipe.getDirections());
        assertEquals(URL,recipe.getUrl());
        assertEquals(COOK_TIME,recipe.getCookTime());
        assertEquals(PREP_TIME,recipe.getPrepTime());
        assertEquals(SERVING,recipe.getServing());
        assertEquals(SOURCE,recipe.getSource());
        assertEquals(DIFFICULTY,recipe.getDifficulty());
        assertEquals(2,recipe.getCategories().size());
        assertEquals(2,recipe.getIngredients().size());

    }


}
