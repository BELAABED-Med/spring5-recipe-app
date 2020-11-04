package com.beles.services;

import com.beles.commands.IngredientCommand;
import com.beles.domain.Ingredient;
import com.beles.domain.Recipe;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
