package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateSoldRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;

public interface ISoldService {
    BaseResponse list();
    void create(CreateSoldRequest request);
}
