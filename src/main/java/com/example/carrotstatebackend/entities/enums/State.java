package com.example.carrotstatebackend.entities.enums;

import lombok.Getter;

@Getter
public enum State {
    ACTIVE(true),
    INACTIVE(false);

    private final Boolean statusCode;

    State(Boolean code){
        this.statusCode = code;
    }
}
