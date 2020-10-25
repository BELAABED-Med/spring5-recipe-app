package com.beles.services;

import com.beles.domain.Recipe;
import com.beles.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
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
            throw new RuntimeException("Recipe Not Found");
        }
        return recipeOptional.get();
        //other method
//        recipeRepository.findById(id).orElse(null);

    }
}
