package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class UpdatePremiseRequest {
    @NotNull
    private String name;

    @NotNull
    private String Location;

    @NotNull
    private String description;

    @NotNull
    private Float size;

    @NotNull
    private Float price;

    @NotNull
    private Boolean soldOut;
}
