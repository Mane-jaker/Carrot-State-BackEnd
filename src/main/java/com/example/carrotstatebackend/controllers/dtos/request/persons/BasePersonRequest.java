package com.example.carrotstatebackend.controllers.dtos.request.persons;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class BasePersonRequest {
    @NotNull
    private String name;
    @NotNull @Email
    private String email;
    @NotNull
    private String password;
}
