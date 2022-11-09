package com.example.carrotstatebackend.controllers.dtos.request;

import com.example.carrotstatebackend.entities.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateSoldRequest {
    private Agent agent;
    private Owner owner;
    private House house;
    private Plot plot;
    private Premise premise;
}
