package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateImageRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetImageResponse;

import java.util.List;

public interface IImageHouseService {
    List<GetImageResponse> list();
    void saveImage(CreateImageRequest request);
}