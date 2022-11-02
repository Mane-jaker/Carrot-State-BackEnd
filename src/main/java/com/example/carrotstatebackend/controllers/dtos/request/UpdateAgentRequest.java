package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class UpdateAgentRequest {

    private String name;

    private String password;

    private String email;

    private String profilePicture;

    private int numberOfSales;

    private int numberOfPropierties;

    private Boolean state;
}
