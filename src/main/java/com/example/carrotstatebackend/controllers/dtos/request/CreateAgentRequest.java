package com.example.carrotstatebackend.controllers.dtos.request;  

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateAgentRequest{

    private String name;

    private String password;

    private String mail;

    private String profilePhoto;

    private int numberOfSales;

    private int numberOfPropierties;

    private Boolean state;
}
