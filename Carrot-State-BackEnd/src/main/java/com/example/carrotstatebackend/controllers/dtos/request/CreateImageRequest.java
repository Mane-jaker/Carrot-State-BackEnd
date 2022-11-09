package com.example.carrotstatebackend.controllers.dtos.request;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class CreateImageRequest {
    
    private House house;
    private Plot plot;
    private Premise premise;
    private String url;

}
