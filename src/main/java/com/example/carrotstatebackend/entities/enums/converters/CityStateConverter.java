package com.example.carrotstatebackend.entities.enums.converters;

import com.example.carrotstatebackend.exceptions.NotValidCityCodeException;
import com.example.carrotstatebackend.entities.enums.CityState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CityStateConverter implements AttributeConverter<CityState, String> {

    @Override
    public String convertToDatabaseColumn(CityState cityState) {
        if (cityState == null) return null;
        return cityState.getLocationCode();
    }

    @Override
    public CityState convertToEntityAttribute(String LocationCode) {
        if (LocationCode == null) return null;
        return Stream.of(CityState.values())
                .filter(c -> c.getLocationCode().equals(LocationCode))
                .findFirst().orElseThrow(() -> new NotValidCityCodeException(LocationCode));
    }
}
