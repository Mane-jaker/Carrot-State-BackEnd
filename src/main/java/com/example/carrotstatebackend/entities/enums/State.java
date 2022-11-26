package com.example.carrotstatebackend.entities.enums;

import lombok.Getter;

@Getter
public enum State {
    ACTIVE("OK"),
    INACTIVE("IN");

    private final String statusCode;

    State(String code){
        this.statusCode = code;
    }
}
