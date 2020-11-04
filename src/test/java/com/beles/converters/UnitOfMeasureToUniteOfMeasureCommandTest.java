package com.beles.converters;

import com.beles.commands.UnitOfMeasureCommand;
import com.beles.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUniteOfMeasureCommandTest {


    private static final Long ID=1l;
    private static final String UOM="Cup";

    UnitOfMeasureToUniteOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter=new UnitOfMeasureToUniteOfMeasureCommand();
    }

    @Test
    void convertTestNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convertTestEmpty() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        //Given
        UnitOfMeasure unitOfMeasure=new UnitOfMeasure();
        unitOfMeasure.setId(ID);
        unitOfMeasure.setUom(UOM);

        //When
        UnitOfMeasureCommand command=converter.convert(unitOfMeasure);

        assertNotNull(command);
        assertNotNull(unitOfMeasure);
        assertEquals(ID,command.getId());
        assertEquals(UOM,command.getUom());

    }
}
