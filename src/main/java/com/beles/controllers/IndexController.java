package com.beles.controllers;

import com.beles.domain.Category;
import com.beles.domain.UnitOfMeasure;
import com.beles.repositories.CategoryRepository;
import com.beles.repositories.RecipeRepository;
import com.beles.repositories.UnitOfMeasureRepository;
import com.beles.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    String getIndexPage(Model model){
        log.debug("Getting Index Page");
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }

}
