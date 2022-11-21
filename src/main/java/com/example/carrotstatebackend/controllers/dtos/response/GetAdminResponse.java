package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class GetAdminResponse {
    private Integer id;
    private String name;
    private String email;
}
