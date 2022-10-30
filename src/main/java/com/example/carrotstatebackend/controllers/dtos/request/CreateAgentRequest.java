package com.example.carrotstatebackend.controllers.dtos.request;  

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateAgentRequest{

    private String name;

    private String password;

    private String mail;
/*
    private int salesNumber;

    private int propertiesNumber;

    private String profilePhoto;
    lastPropSold;
    fk
    private Boolean state;
*/
}
