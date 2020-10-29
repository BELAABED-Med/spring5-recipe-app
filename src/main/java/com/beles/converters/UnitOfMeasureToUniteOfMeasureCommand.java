package com.beles.converters;

import com.beles.commands.UnitOfMesureCommand;
import com.beles.domain.UnitOfMeasure;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUniteOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMesureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMesureCommand convert(UnitOfMeasure source) {
        if (source == null) {
            return null;
        }
        final UnitOfMesureCommand unitOfMesureCommand=new UnitOfMesureCommand();
        unitOfMesureCommand.setId(source.getId());
        unitOfMesureCommand.setUom(source.getUom());
        return unitOfMesureCommand;
    }
}
