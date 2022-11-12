package com.example.carrotstatebackend.controllers.dtos.response;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class GetImageResponse {

    private Long id;
    private String url;
    private Premise premise;
    private Plot plot;
    private House house;
}
