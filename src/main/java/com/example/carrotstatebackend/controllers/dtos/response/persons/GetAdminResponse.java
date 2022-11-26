package com.example.carrotstatebackend.controllers.dtos.response.persons;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class GetAdminResponse {
    private Integer id;
    private String name;
    private String email;
}
