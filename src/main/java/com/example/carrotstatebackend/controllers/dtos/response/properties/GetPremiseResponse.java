package com.example.carrotstatebackend.controllers.dtos.response.properties;

import com.example.carrotstatebackend.controllers.dtos.response.GetImageResponse;
import com.example.carrotstatebackend.entities.enums.CityState;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GetPremiseResponse {
   private Long id;
   private String name;
   private String location;
   private String description;
   private Float size;
   private Float price;
   private Boolean soldOut;
   private CityState cityState;
   private List<GetImageResponse> images;
}
