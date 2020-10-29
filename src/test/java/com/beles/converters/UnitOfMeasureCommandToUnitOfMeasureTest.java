package com.beles.converters;

import com.beles.commands.UnitOfMesureCommand;
import com.beles.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final Long ID=1l;
    private static final String UOM="Cup";

    UnitOfMeasureCommandToUnitOfMeasure uniteOfMeasureCommandconverter;

    @BeforeEach
    void setUp() {
        uniteOfMeasureCommandconverter=new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void convertTestNull() {
        assertNull(uniteOfMeasureCommandconverter.convert(null));
    }

    @Test
    void convertTestEmpty() {
        assertNotNull(uniteOfMeasureCommandconverter.convert(new UnitOfMesureCommand()));
    }

    @Test
    void convert() {
        //Given
        UnitOfMesureCommand command=new UnitOfMesureCommand();
        command.setId(ID);
        command.setUom(UOM);

        //When
        UnitOfMeasure unitOfMeasure=uniteOfMeasureCommandconverter.convert(command);

        assertNotNull(command);
        assertNotNull(unitOfMeasure);
        assertEquals(ID,unitOfMeasure.getId());
        assertEquals(UOM,unitOfMeasure.getUom());

    }
}
