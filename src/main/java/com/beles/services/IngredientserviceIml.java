package com.beles.services;

import com.beles.commands.IngredientCommand;
import com.beles.converters.IngredientCommandToIngredient;
import com.beles.converters.IngredientToIngredientCommand;
import com.beles.domain.Ingredient;
import com.beles.domain.Recipe;
import com.beles.repositories.RecipeRepository;
import com.beles.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredientserviceIml implements IngredientService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredient ingredientCommandConvert;
    private final IngredientToIngredientCommand ingredientConverter;
    private final RecipeRepository recipeRepository;

    public IngredientserviceIml(UnitOfMeasureRepository unitOfMeasureRepository,
                                IngredientToIngredientCommand ingredientconverter,
                                IngredientCommandToIngredient ingredientCommandConvert,
                                RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientConverter = ingredientconverter;
        this.ingredientCommandConvert = ingredientCommandConvert;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> optionalRecipe=recipeRepository.findById(recipeId);

        if(!optionalRecipe.isPresent()){
            log.error("Recipe Not Found !! id : "+recipeId);
        }

        Recipe recipe=optionalRecipe.get();

        Optional<IngredientCommand> optionalIngredientCommand=recipe.getIngredients().stream()
                .filter(ingredient->ingredient.getId().equals(ingredientId))
                .map(ingredient ->  ingredientConverter.convert(ingredient)).findFirst();

        if (!optionalIngredientCommand.isPresent()) {
            log.error("Ingredient Not Found !!!");
        }
        return optionalIngredientCommand.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional=recipeRepository.findById(command.getRecipeId());

        if (!recipeOptional.isPresent()) {
            log.error("Recipe Not Found");
            return new IngredientCommand();
        }else{
            Recipe recipe=recipeOptional.get();
            Optional<Ingredient> ingredientOptional=recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()){
                Ingredient ingredientFound=ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(()->new RuntimeException("Unit Of Measure Not Found"))
                );
            }else{
                //add new Ingredient
                Ingredient ingredient=ingredientCommandConvert.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }
            Recipe savedrecipe=recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional=savedrecipe.getIngredients().stream()
                    .filter(recipeIngredient->recipeIngredient.getId().equals(command.getId()))
                    .findFirst();

            if(!savedIngredientOptional.isPresent()){

                savedIngredientOptional=savedrecipe.getIngredients().stream()
                        .filter(recipeIngredient->recipeIngredient.getDescription().equals(command.getDescription()))
                        .filter(recipientIngredient->recipientIngredient.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients->recipeIngredients.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }
            return ingredientConverter.convert(savedIngredientOptional.get());
        }
    }

    @Override
    public void deleteById(Long recipeId, Long ingredientId) {
//        IngredientCommand detachedIngredient=findByRecipeIdAndIngredientId(recipeId.longValue(), ingredientId.longValue());
        Optional<Recipe> optionalRecipe=recipeRepository.findById(recipeId.longValue());
        if (!optionalRecipe.isPresent()) {
            log.error("Recipe Not Found");
        }
        Recipe recipe=optionalRecipe.get();
        Optional<Ingredient> optionalIngredient=recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst();
        if (!optionalIngredient.isPresent()) {
            log.error("Ingredient Not Found");
        }else{
            log.info("Ingredient Is Found");
            Ingredient ingredientToDelete=optionalIngredient.get();
            ingredientToDelete.setRecipe(null);
            recipe.getIngredients().remove(optionalIngredient.get());
            recipeRepository.save(recipe);
        }

    }
}
