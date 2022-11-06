package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePlotRequest {

    private String name;

    private String location;

    private String description;

    private Float price;

    private Float size;


}
