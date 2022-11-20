package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetClientResponse {
    private Long id;
    private String name;
    private String contact;
    private Float budget;
}
