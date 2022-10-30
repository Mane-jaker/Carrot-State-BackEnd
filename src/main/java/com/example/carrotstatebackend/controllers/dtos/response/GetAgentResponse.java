package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAgentResponse {
    private Long id;

    private String name;

    private String mail;

    private String profilePicture;

    private String password;

    private String status;

}
