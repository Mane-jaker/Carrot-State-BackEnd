package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateImageRequest;

public interface IImageService{
    void saveHouseImage(CreateImageRequest request);
    void savePremiseImage(CreateImageRequest request);
    void savePlotImage(CreateImageRequest request);
}