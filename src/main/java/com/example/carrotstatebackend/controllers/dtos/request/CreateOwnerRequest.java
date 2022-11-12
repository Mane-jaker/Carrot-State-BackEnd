package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreateOwnerRequest {

    @NotNull
    private Long prospectiveBuyerId;

    @NotNull
    private Long idProperty;
}
