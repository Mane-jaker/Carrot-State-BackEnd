package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Builder;
import lombok.Getter;


@Builder @Getter
public class GetSoldResponse {
    private Long id;
    private String date;
    private Float commission;
    private GetOwnerResponse owner;
    private GetAgentResponse agent;
    private GetHouseResponse house;
    private GetPlotResponse plot;
    private GetPremiseResponse premise;
}
