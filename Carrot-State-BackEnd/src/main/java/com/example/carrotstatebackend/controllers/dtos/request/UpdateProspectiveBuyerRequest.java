package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateProspectiveBuyerRequest {

    private String name;

    private String contact;

    private Float budget;
}
