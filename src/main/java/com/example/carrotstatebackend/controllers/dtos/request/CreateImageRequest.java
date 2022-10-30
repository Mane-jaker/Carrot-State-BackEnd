package com.example.carrotstatebackend.controllers.dtos.request;

import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateImageRequest {
    
    private House house;
    private Plot plot;
    private Premise premise;
    private String url;

}
