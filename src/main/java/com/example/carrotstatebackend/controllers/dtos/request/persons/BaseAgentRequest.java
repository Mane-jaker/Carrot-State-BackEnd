package com.example.carrotstatebackend.controllers.dtos.request.persons;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter @Getter
public class BaseAgentRequest extends BasePersonRequest{
    @NotNull
    private String managerCode;
}
