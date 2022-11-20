package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreateRealStateRequest {

    @NotNull
    private String name;

    @NotNull @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Float commissionAgent;

}
 
