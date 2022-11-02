package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class UpdateAgentRequest {

    private String name;

    private String mail;

    private String password;
    /*
    private int salesNumber;

    private int propertiesNumber;

    private String profilePhoto;
    lastPropSold;
    fk
    private Boolean state;
    */
}
