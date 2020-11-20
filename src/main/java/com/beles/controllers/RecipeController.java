package com.beles.controllers;

import com.beles.commands.RecipeCommand;
import com.beles.exceptions.NotFoundException;
import com.beles.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

import static java.lang.Long.parseLong;
import static java.lang.Long.valueOf;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    public static final String RECIPE_FORM = "recipe/recipeform";
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findById(parseLong(id)));
        return "recipe/show";
    }

    @RequestMapping("new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommand());
        return RECIPE_FORM;
    }

    @PostMapping("")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult result){
        if (result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return RECIPE_FORM;
        }
        RecipeCommand savedCommand=recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+savedCommand.getId()+"/show";
    }

    @RequestMapping("{id}/update")
    public String updateRecipe(@PathVariable String id,Model model){
        model.addAttribute("recipe",recipeService.findCommandById(valueOf(id)));
        return RECIPE_FORM;
    }

    @RequestMapping("{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        log.debug("Deleting id : "+id);
        recipeService.deleteRecipe(valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handling Not Found Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("404Error");
        modelAndView.addObject("exception",exception);
        return modelAndView;
    }


}
