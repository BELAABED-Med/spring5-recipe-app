package com.beles.converters;

import com.beles.commands.CategoryCommand;
import com.beles.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    private static final Long ID=1L;
    private static final String DESCRIPTION="Description";

    CategoryToCategoryCommand categoryToCategoryCommand;

    @BeforeEach
    void setUp() {
        categoryToCategoryCommand=new CategoryToCategoryCommand();
    }

    @Test
    void convertTestNull() {
        assertNull(categoryToCategoryCommand.convert(null));
    }

    @Test
    void convertTestEmpty() {
        assertNotNull(new Category());
    }

    @Test
    void convert() {

        //Given
        Category category=new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);
        //When
        CategoryCommand cat=categoryToCategoryCommand.convert(category);
        //Then
        assertNotNull(cat);
        assertEquals(ID,cat.getId());
        assertEquals(DESCRIPTION,cat.getDescription());

    }
}
