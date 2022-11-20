package com.example.carrotstatebackend.controllers.dtos.response;

import com.example.carrotstatebackend.entities.enums.CityState;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GetHouseResponse {
    private Long id;
    private String name;
    private String location;
    private String description;
    private Integer bathroomNum;
    private Float price;
    private Integer rooms;
    private Integer floors;
    private Boolean SoldOut;
    private CityState cityState;
    private List<GetImageResponse> images;
}
