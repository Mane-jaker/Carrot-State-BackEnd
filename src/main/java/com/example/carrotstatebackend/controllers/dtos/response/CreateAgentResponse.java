package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAgentResponse{
    private String name;

    private String password;

    private String mail;

    private String profilePhoto;

    private int numberOfSales;

    private int numberOfPropierties;

    private Boolean state;
}
 
