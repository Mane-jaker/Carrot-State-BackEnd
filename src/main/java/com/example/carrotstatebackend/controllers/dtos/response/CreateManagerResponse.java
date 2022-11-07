package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateManagerResponse{

     private Long id;

     private String name;

     private String Email;

     private String managerCode;

     private Float commissionAgent;
}
 
