package com.example.carrotstatebackend.controllers.dtos.request.persons;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class BaseClientRequest extends BasePersonRequest{
    @NotNull
    private String contact;
    @NotNull
    private Float budget;
}
