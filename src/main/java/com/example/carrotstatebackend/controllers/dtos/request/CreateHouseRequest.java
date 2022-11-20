package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreateHouseRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Float price;

    @NotNull
    private Float size;

    @NotNull
    private String location;

    @NotNull
    private String cityCode;

    @NotNull
    private Integer bathRoomNum;

    @NotNull
    private Integer rooms;

    @NotNull
    private Integer floors;

}
