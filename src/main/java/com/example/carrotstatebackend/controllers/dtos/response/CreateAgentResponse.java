package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAgentResponse{

    private String name;

    private String password;

    private String email;

    private String profilePicture;

    private Integer numberOfSales;

    private Integer numberOfPropierties;

    private Boolean state;
}
 
