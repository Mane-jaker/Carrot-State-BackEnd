package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateHouseRequest {

    private String name;

    private String description;

    private Float price;

    private Float size;

    private String location;

    private Integer bathRoomNum;

    private Integer rooms;

    private Integer floors;

}
