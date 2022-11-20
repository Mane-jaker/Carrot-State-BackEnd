package com.example.carrotstatebackend.entities.enums;

import lombok.Getter;

@Getter
public enum CityState {
    TUXTLA_GUTIERREZ_CHIAPAS("TXGZCH"),
    SUCHIAPA_CHIAPAS("SUCHCH"),
    COITA_CHIAPAS("COICH"),
    DUBAI_EMIRATOS_ARABES("DUBAIEA");

    private final String LocationCode;

    CityState(String code){
        this.LocationCode = code;
    }
}
