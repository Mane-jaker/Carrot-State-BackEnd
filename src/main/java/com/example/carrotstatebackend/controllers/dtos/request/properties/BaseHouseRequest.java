package com.example.carrotstatebackend.controllers.dtos.request.properties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class BaseHouseRequest extends BasePropertyRequest {
    @NotNull
    private Integer bathRoomNum;
    @NotNull
    private Integer rooms;
    @NotNull
    private Integer floors;
}
