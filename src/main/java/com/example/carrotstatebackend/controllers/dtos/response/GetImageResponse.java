package com.example.carrotstatebackend.controllers.dtos.response;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter @Builder
public class GetImageResponse {
    private String url;
}
