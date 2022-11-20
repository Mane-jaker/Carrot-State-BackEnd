package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class UpdateClientRequest {

    @NotNull
    private String name;

    @NotNull
    private String contact;

    @NotNull
    private Float budget;
}
