package com.example.carrotstatebackend.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestFilters {
    private Float budget;
    private String cityCode;
    private Boolean useKeyWord;
}
