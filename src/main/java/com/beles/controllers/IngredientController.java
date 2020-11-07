package com.beles.controllers;

import com.beles.commands.IngredientCommand;
import com.beles.commands.UnitOfMeasureCommand;
import com.beles.services.IngredientService;
import com.beles.services.RecipeService;
import com.beles.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.lang.Long.valueOf;
@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String ingredientList(@PathVariable String recipeId,Model model){
        model.addAttribute("recipe",recipeService.findCommandById(valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String ShowIngredient(@PathVariable String recipeId,@PathVariable String ingredientId,Model model){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(valueOf(recipeId),valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String showIngredientForm(@PathVariable String recipeId,@PathVariable String ingredientId,Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(valueOf(recipeId), valueOf(ingredientId)));
        model.addAttribute("uomList",unitOfMeasureService.findAllUom());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand=ingredientService.saveIngredientCommand(command);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients";

    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String addIngredientToRecipe(@PathVariable String recipeId,Model model){
        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingredient",ingredientCommand);
        //init uom

        model.addAttribute("uomList",unitOfMeasureService.findAllUom());

        return "recipe/ingredient/ingredientform";

    }



    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId,@PathVariable String ingredientId){
        log.info("Invocation of delete Method,deleting ingredient "+ingredientId);
        ingredientService.deleteById(Long.valueOf(recipeId),Long.valueOf(ingredientId));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

}
