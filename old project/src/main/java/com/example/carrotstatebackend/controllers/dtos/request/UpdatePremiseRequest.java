package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdatePremiseRequest {
    private String name;

    private String Location;

    private String description;

    private Float size;

    private Float price;

    private Boolean soldOut;
}
