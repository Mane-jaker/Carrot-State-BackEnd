package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetPremiseResponse {
   private Long id;

   private String name;

   private String Location;

   private String description;

   private Float size;

   private Float price;

   private Boolean soldOut;

   private GetOwnerResponse owner;
}
