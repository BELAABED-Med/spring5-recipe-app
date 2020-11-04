package com.beles.converters;

import com.beles.commands.IngredientCommand;
import com.beles.domain.Ingredient;
import com.beles.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    private static final Long ID=1L;
    private static final Long ID_UOM=1L;
    private static final String DESCRIPTION="Description";
    private static final BigDecimal AMOUNT= BigDecimal.valueOf(10);

    IngredientToIngredientCommand ingredientToIngredientCommand;

    @BeforeEach
    void setUp() {
        ingredientToIngredientCommand=new IngredientToIngredientCommand(new UnitOfMeasureToUniteOfMeasureCommand());
    }

    @Test
    void convertTestNull() {
        assertNull(ingredientToIngredientCommand.convert(null));
    }

    @Test
    void convertTestEmpty() {
        assertNotNull(ingredientToIngredientCommand.convert(new Ingredient()));
    }

    @Test
    void convert() {
        //Given
        Ingredient ingredient=new Ingredient();
        ingredient.setId(ID);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        UnitOfMeasure uom=new UnitOfMeasure();
        uom.setId(ID_UOM);
        ingredient.setUom(uom);

        //When
        IngredientCommand ingredientCommand=ingredientToIngredientCommand.convert(ingredient);

        //Then
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUom());
        assertEquals(ID,ingredientCommand.getId());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(AMOUNT,ingredientCommand.getAmount());
        assertEquals(ID_UOM,ingredientCommand.getUom().getId());

    }
}
