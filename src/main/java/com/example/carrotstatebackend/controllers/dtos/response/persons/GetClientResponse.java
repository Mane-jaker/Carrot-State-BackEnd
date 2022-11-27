package com.example.carrotstatebackend.controllers.dtos.response.persons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class GetClientResponse {
    private Long id;
    private String name;
    private String email;
    private String contact;
    private Float budget;
    private String profilePicture;
}
