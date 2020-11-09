package com.beles.converters;

import com.beles.commands.CategoryCommand;
import com.beles.commands.IngredientCommand;
import com.beles.commands.RecipeCommand;
import com.beles.domain.Recipe;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientCommandConverter;
    private final CategoryCommandToCategory categoryCommandConverter;
    private final NotesCommandToNotes notesCommandConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandConverter,
                                 CategoryCommandToCategory categoryCommandConverter,
                                 NotesCommandToNotes notesCommandConverter) {
        this.ingredientCommandConverter = ingredientCommandConverter;
        this.categoryCommandConverter = categoryCommandConverter;
        this.notesCommandConverter = notesCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        else{
            final Recipe recipe = new Recipe();
            recipe.setId(source.getId());
            recipe.setDescription(source.getDescription());
            recipe.setPrepTime(source.getPrepTime());
            recipe.setCookTime(source.getCookTime());
            recipe.setServing(source.getServing());
            recipe.setSource(source.getSource());
            recipe.setUrl(source.getUrl());
            recipe.setImage(source.getImage());
            recipe.setDifficulty(source.getDifficulty());
            recipe.setDirections(source.getDirections());
            recipe.setNotes(notesCommandConverter.convert(source.getNotes()));

            if(source.getCategories() != null && source.getCategories().size() >0){
                source.getCategories()
                        .forEach(categoryCommand -> recipe.getCategories().add(categoryCommandConverter.convert(categoryCommand)));
            }

            if(source.getIngredients() != null && source.getIngredients().size() >0){
                source.getIngredients()
                        .forEach(ingredientcommand -> recipe.getIngredients().add(ingredientCommandConverter.convert(ingredientcommand)));
            }

            return recipe;
        }
    }
}
