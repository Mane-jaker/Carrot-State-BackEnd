package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetManagerResponse {
    private Long id;
    private String name;
    private String mail;
    private String profilePicture;
    private String managerCode;
    private String password;
    private Float commissionAgent;
}
