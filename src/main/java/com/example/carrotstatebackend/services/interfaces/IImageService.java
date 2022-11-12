package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateImageRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetImageResponse;

import java.util.List;

public interface IImageService{
    List<GetImageResponse> list();
    void saveHouseImage(CreateImageRequest request);
    void savePremiseImage(CreateImageRequest request);
    void savePlotImage(CreateImageRequest request);
}