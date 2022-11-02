package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOwnerRequest {

    private String name;

    private String contact;

    private String propierty;
}
