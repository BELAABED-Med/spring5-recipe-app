package com.beles.repositories;

import com.beles.commands.RecipeCommand;
import com.beles.domain.Recipe;
import org.springframework.data.repository.CrudRepository;


public interface RecipeRepository extends CrudRepository<Recipe,Long> {

}
