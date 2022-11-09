package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetLoginResponse {
    private Boolean success;
    private Object loged;
}
