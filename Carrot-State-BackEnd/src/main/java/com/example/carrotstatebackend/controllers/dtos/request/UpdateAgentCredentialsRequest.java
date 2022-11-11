package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAgentCredentialsRequest {

    private String mail;

    private String password;
}
