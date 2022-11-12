package com.example.carrotstatebackend.controllers.dtos.request;  

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter @Getter
public class CreateAgentRequest{

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull @Email
    private String email;

    @NotNull
    private Long managerCode;
}
