package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateManagerRequest{

    private String name;

    private String mail;

    private String password;

    private String managerCode;

    private Float commissionAgent;
}
 
