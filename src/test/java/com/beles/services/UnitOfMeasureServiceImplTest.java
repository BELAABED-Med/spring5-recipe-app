package com.beles.services;

import com.beles.commands.UnitOfMeasureCommand;
import com.beles.converters.UnitOfMeasureToUniteOfMeasureCommand;
import com.beles.domain.UnitOfMeasure;
import com.beles.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUniteOfMeasureCommand unitOfMeasureConverter=new UnitOfMeasureToUniteOfMeasureCommand();

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    MockMvc mockMvc;

    UnitOfMeasureService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service=new UnitOfMeasureServiceImpl(unitOfMeasureConverter,unitOfMeasureRepository);
    }

    @Test
    void findAllUom() {

        //Given
        Set<UnitOfMeasure> uomSet=new HashSet<>();
        UnitOfMeasure uom1=new UnitOfMeasure();
        uom1.setId(1L);
        UnitOfMeasure uom2=new UnitOfMeasure();
        uom1.setId(2L);
        uomSet.add(uom1);
        uomSet.add(uom2);
        when(unitOfMeasureRepository.findAll()).thenReturn(uomSet);

        //When
        Set<UnitOfMeasureCommand> unitOfMeasureCommandSet=service.findAllUom();

        //Then
        assertEquals(2,unitOfMeasureCommandSet.size());
        verify(unitOfMeasureRepository,times(1)).findAll();

    }
}
