package com.beles.commands;

import com.beles.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RecipeCommand {
    private Long id;

    @NotBlank
    @Size(min=3,max=255)
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(299)
    private Integer serving;
    private String source;

    @URL
    private String url;

   @NotBlank
    private String directions;
    private Byte[] image;
    private Set<IngredientCommand> ingredients= new HashSet<>();
    private NotesCommand notes;
    private Difficulty difficulty;
    private Set<CategoryCommand> categories= new HashSet<>();

    public RecipeCommand() {
    }
}
