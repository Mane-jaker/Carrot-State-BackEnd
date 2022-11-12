package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class LoginRequest {

    @Email @NotNull
    private String email;

    @NotNull
    private String password;
}
