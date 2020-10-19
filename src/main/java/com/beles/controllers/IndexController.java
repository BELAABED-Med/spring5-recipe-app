package com.beles.controllers;

import com.beles.domain.Category;
import com.beles.domain.UnitOfMeasure;
import com.beles.repositories.CategoryRepository;
import com.beles.repositories.RecipeRepository;
import com.beles.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Slf4j
public class IndexController {

    private final RecipeRepository recipeRepository;

    public IndexController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"","/","/index"})
    String getIndexPage(Model model){
        log.debug("Getting Index Page");
        model.addAttribute("recipes",recipeRepository.findAll());
        return "index";
    }

}
