package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAgentResponse {

    private Long id;

    private String name;

    private String password;

    private String email;

    private String profilePicture;

    private int numberOfSales;

    private int numberOfPropierties;

    private Boolean state;
}
