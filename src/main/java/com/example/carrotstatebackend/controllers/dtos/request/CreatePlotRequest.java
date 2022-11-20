package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreatePlotRequest {

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private String cityCode;

    @NotNull
    private String description;

    @NotNull
    private Float price;

    @NotNull
    private Float size;


}
