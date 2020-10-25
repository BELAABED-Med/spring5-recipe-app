package com.beles.controllers;

import com.beles.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findById(parseLong(id)));
        return "recipe/show";
    }


}
