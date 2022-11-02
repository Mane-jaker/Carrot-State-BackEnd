package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateHouseRequest {
    private String location;

    private String description;

    private Integer bathroomNum;

    private Integer rooms;

    private Integer floors;
}
