package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateManagerResponse{

     private String name;

     private String mail;

     private String password;

     private String managerCode;

     private Float commissionAgent;
}
 
