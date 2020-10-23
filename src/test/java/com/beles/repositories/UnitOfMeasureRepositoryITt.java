package com.beles.repositories;

import com.beles.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UnitOfMeasureRepositoryITt {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUom() {
        Optional<UnitOfMeasure> uomOptional=unitOfMeasureRepository.findByUom("Cup");
        assertEquals("Cup",uomOptional.get().getUom());
    }

    @Test
    void findByUomTeaSpoon() {
        Optional<UnitOfMeasure> uomOptional=unitOfMeasureRepository.findByUom("TableSpoon");
        assertEquals("TableSpoon",uomOptional.get().getUom());
    }

}
