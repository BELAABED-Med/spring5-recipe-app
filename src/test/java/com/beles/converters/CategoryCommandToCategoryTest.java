package com.beles.converters;

import com.beles.commands.CategoryCommand;
import com.beles.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    private static Long ID=1L;
    private static String DESCRIPTION="THIS IS A DESCRIPTION OF A CATEGOTY";

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter=new CategoryCommandToCategory();
    }

    @Test
    void convertTestNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convertTestEmpty() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {

        //Given
        CategoryCommand cc =new CategoryCommand();
        cc.setId(ID);
        cc.setDescription(DESCRIPTION);

        //When
        Category cat=converter.convert(cc);

        //then
        assertEquals(ID,cat.getId());
        assertEquals(DESCRIPTION,cat.getDescription());



    }
}
