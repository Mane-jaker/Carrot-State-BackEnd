package com.example.carrotstatebackend.controllers.dtos.request.properties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class BasePropertyRequest{
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Float size;
    @NotNull
    private Float price;
    @NotNull
    private String location;
    @NotNull
    private String cityCode;
}
