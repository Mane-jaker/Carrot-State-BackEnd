package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class UpdateAgentRequest {

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull @Email
    private String email;
    
    private String profilePicture;

    private Boolean state;
}