package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOwnerResponse {
    private Long id;

    private String name;

    private String contact;

    private Object property;
}
