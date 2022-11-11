package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateManagerCredentialsRequest {

    private String mail;

    private String password;

}
