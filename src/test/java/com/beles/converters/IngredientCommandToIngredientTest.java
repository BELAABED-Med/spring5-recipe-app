package com.beles.converters;

import com.beles.commands.IngredientCommand;
import com.beles.commands.UnitOfMesureCommand;
import com.beles.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
class IngredientCommandToIngredientTest {
    private static final Long ID=1L;
    private static final Long ID_UOM=1L;
    private static final String DESCRIPTION="Description";
    private static final BigDecimal AMOUNT= BigDecimal.valueOf(10);

    IngredientCommandToIngredient ingredientCommandToIngredient;

    @BeforeEach
    void setUp() {
        ingredientCommandToIngredient=new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void convertTestNull() {
        assertNull(ingredientCommandToIngredient.convert(null));
    }

    @Test
    void convertTestEmpty() {
        assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        //Given
        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);
        UnitOfMesureCommand uomc=new UnitOfMesureCommand();
        uomc.setId(ID_UOM);
        ingredientCommand.setUom(uomc);

        //When
        Ingredient ingredient=ingredientCommandToIngredient.convert(ingredientCommand);

        //Then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID,ingredient.getId());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(AMOUNT,ingredient.getAmount());
        assertEquals(ID_UOM,ingredient.getUom().getId());

    }

}
