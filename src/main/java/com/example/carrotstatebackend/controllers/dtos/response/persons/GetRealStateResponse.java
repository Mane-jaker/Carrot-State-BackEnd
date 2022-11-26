package com.example.carrotstatebackend.controllers.dtos.response.persons;

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
    private Float commissionAgent;
}
