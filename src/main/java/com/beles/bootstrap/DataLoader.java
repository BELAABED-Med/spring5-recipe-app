package com.beles.bootstrap;

import com.beles.domain.*;
import com.beles.repositories.CategoryRepository;
import com.beles.repositories.RecipeRepository;
import com.beles.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
@Profile("default")
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading bootstrap Data ...");
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes=new ArrayList<Recipe>();
        Optional<Category> americanOptionalCategory=categoryRepository.findByDescription("Americain");
        Optional<Category> mexicanOptionalCategory=categoryRepository.findByDescription("Mexican");
        Optional<Category> italienOptionalCategory=categoryRepository.findByDescription("Italien");

        Optional<UnitOfMeasure> cupUOM=unitOfMeasureRepository.findByUom("Cup");
        Optional<UnitOfMeasure> teaspoonUOM=unitOfMeasureRepository.findByUom("Teaspoon");
        Optional<UnitOfMeasure> tableSpoonUOM=unitOfMeasureRepository.findByUom("TableSpoon");
        Optional<UnitOfMeasure> cloveUOM=unitOfMeasureRepository.findByUom("Clove");
        Optional<UnitOfMeasure> eachUOM=unitOfMeasureRepository.findByUom("Each");
        Optional<UnitOfMeasure> dashUOM=unitOfMeasureRepository.findByUom("dash");

        Recipe r1=new Recipe();
        Notes n1=new Notes();
        n1.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "Spicy Grilled Chicken TacosThe ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!"
        );
        r1.setNotes(n1);
        r1.setDescription("Recipe 1");
        r1.setCookTime(15);
        r1.setPrepTime(20);
        r1.setDifficulty(Difficulty.MODERATE);
        r1.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges."
        );
        r1.addIngredient(new Ingredient("ancho chili powder",new BigDecimal(2), tableSpoonUOM.get()));
        r1.addIngredient(new Ingredient("dried oregano",new BigDecimal(1), teaspoonUOM.get()));
        r1.addIngredient(new Ingredient("dried cumin",new BigDecimal(1), teaspoonUOM.get()));
        r1.addIngredient(new Ingredient("sugar",new BigDecimal(1), teaspoonUOM.get()));
        r1.addIngredient(new Ingredient("salt",new BigDecimal(0.5), teaspoonUOM.get()));
        r1.addIngredient(new Ingredient("garlic, finely chopped",new BigDecimal(1), cloveUOM.get()));
        r1.addIngredient(new Ingredient("finely grated orange zest",new BigDecimal(1), tableSpoonUOM.get()));
        r1.addIngredient(new Ingredient("fresh-squeezed orange juice",new BigDecimal(3), tableSpoonUOM.get()));
        r1.addIngredient(new Ingredient("olive oil",new BigDecimal(2), tableSpoonUOM.get()));
        r1.addIngredient(new Ingredient("skinless, boneless chicken thighs",new BigDecimal(5), eachUOM.get()));

        r1.getCategories().add(americanOptionalCategory.get());
        r1.getCategories().add(mexicanOptionalCategory.get());

        r1.setImage(null);
        r1.setSource("--------");

        recipes.add(r1);

        //-------------- Second Recipe -----------------//
        Recipe r2=new Recipe();
        Notes n2=new Notes();
        n2.setRecipeNotes("Guacamole is best eaten right after it’s made. Like apples, avocados start to oxidize and turn brown once they’ve been cut. That said, the acid in the lime juice you add to guacamole can help slow down that process, and if you store the guacamole properly, you can easily make it a few hours ahead if you are preparing for a party.\n" +
                "\n" +
                "The trick to keeping guacamole green is to make sure air doesn’t touch it! Transfer it to a container, cover with plastic wrap, and press down on the plastic wrap to squeeze out any air pockets. Make sure any exposed surface of the guacamole is touching the plastic wrap, not air. This will keep the amount of browning to a minimum.\n" +
                "\n" +
                "You can store the guacamole in the fridge this way for up to three days.\n" +
                "\n" +
                "If you leave the guacamole exposed to air, it will start to brown and discolor. That browning isn’t very appetizing, but the guacamole is still good. You can either scrape off the brown parts and discard, or stir them into the rest of the guacamole.");
        r2.setNotes(n2);
        r2.setDescription("Recipe 2");
        r2.setCookTime(3);
        r2.setPrepTime(10);
        r2.setDifficulty(Difficulty.HARD);
        r2.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve."
        );

        r2.addIngredient(new Ingredient("ripe avocados",new BigDecimal(2), eachUOM.get()));
        r2.addIngredient(new Ingredient("Salt",new BigDecimal(1), teaspoonUOM.get()));
        r2.addIngredient(new Ingredient(" fresh lime juice or lemon juice",new BigDecimal(1), tableSpoonUOM.get()));
        r2.addIngredient(new Ingredient("minced red onion or thinly sliced green onion",new BigDecimal(3), tableSpoonUOM.get()));
        r2.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped Red radishes or jicama, to garnish",new BigDecimal(0.5), eachUOM.get()));
        r2.addIngredient(new Ingredient("freshly grated black pepper",new BigDecimal(1), dashUOM.get()));

        r2.getCategories().add(americanOptionalCategory.get());
        r2.getCategories().add(italienOptionalCategory.get());

        r2.setImage(null);
        r2.setSource("--------");

        recipes.add(r2);
        return recipes;
    }


}
