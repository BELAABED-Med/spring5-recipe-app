package com.beles.services;

import com.beles.commands.RecipeCommand;
import com.beles.converters.RecipeCommandToRecipe;
import com.beles.converters.RecipeToRecipeCommand;
import com.beles.domain.Recipe;
import com.beles.exceptions.NotFoundException;
import com.beles.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService{

    private final RecipeCommandToRecipe recipeCommandConverter;
    private final RecipeToRecipeCommand recipeConverter;
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeCommandToRecipe recipeCommandConverter, RecipeToRecipeCommand recipeConverter, RecipeRepository recipeRepository) {
        this.recipeCommandConverter = recipeCommandConverter;
        this.recipeConverter = recipeConverter;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("i'm in Recipe service");
        Set<Recipe> recipes=new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional=recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw new NotFoundException("Recipe Not Found,For ID  "+id.toString());
        }
        return recipeOptional.get();

    }

    @Override
    public RecipeCommand findCommandById(Long id) {
        return recipeConverter.convert(findById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe=recipeCommandConverter.convert(command);
        log.debug("SAVED DETACHED RECIPE : "+detachedRecipe.getId());
        Recipe savedRecipe=recipeRepository.save(detachedRecipe);
        return recipeConverter.convert(savedRecipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

}
