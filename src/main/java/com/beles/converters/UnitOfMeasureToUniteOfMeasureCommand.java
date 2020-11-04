package com.beles.converters;

import com.beles.commands.UnitOfMeasureCommand;
import com.beles.domain.UnitOfMeasure;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUniteOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if (source == null) {
            return null;
        }
        final UnitOfMeasureCommand unitOfMesureCommand=new UnitOfMeasureCommand();
        unitOfMesureCommand.setId(source.getId());
        unitOfMesureCommand.setUom(source.getUom());
        return unitOfMesureCommand;
    }
}
