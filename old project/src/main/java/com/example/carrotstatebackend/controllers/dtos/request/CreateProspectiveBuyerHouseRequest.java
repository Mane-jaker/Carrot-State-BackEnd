package com.example.carrotstatebackend.controllers.dtos.request;

import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder @Getter
public class CreateProspectiveBuyerHouseRequest {
    private House house;
    private ProspectiveBuyer prospectiveBuyer;
}
