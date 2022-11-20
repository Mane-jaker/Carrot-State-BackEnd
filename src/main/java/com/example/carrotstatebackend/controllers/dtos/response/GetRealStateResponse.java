package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetRealStateResponse {
    private Long id;
    private String name;
    private String Email;
    private Boolean status;
    private String profilePicture;
    private String realStateCode;
    private String password;
    private Float commissionAgent;
}
