package com.example.carrotstatebackend.controllers.dtos.response;

import com.example.carrotstatebackend.controllers.dtos.response.persons.GetAgentResponse;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetClientResponse;
import com.example.carrotstatebackend.controllers.dtos.response.properties.GetHouseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.properties.GetPlotResponse;
import com.example.carrotstatebackend.controllers.dtos.response.properties.GetPremiseResponse;
import lombok.Builder;
import lombok.Getter;


@Builder @Getter
public class GetSoldResponse {
    private Long id;
    private String date;
    private Float commission;
    private GetAgentResponse agent;
    private GetHouseResponse house;
    private GetPlotResponse plot;
    private GetPremiseResponse premise;
    private GetClientResponse client;
}
