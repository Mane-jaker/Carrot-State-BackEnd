package com.example.carrotstatebackend.controllers.dtos.request;

import com.example.carrotstatebackend.entities.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreateSoldRequest {

    @NotNull
    private Agent agent;

    private House house;

    private Plot plot;

    private Premise premise;

    private Client client;
}
