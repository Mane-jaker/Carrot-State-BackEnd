package com.example.carrotstatebackend.controllers.dtos.request.persons;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class BaseRealStateRequest extends BasePersonRequest{
    @NotNull
    private Float commissionAgent;
}
 
