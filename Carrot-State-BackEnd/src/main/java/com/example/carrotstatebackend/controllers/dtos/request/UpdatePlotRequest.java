package com.example.carrotstatebackend.controllers.dtos.request;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdatePlotRequest {

    private String name;

    private String location;

    private String description;

    private Float size;

    private Float price;


}
