package com.example.carrotstatebackend.controllers.dtos.request;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder @Getter
public class CreateImageRequest {
    @NotNull
    private House house;

    @NotNull
    private Plot plot;

    @NotNull
    private Premise premise;

    @NotNull
    private String url;

}
