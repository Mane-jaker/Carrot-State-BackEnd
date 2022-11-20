package com.example.carrotstatebackend.controllers.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class UploadImageResponse {
    private Object property;
    private String url;
}
