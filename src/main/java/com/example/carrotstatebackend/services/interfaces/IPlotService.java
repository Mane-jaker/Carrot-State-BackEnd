package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetPlotResponse;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.Plot;


public interface IPlotService {
    BaseResponse get(Long id);
    BaseResponse create(CreatePlotRequest request, Long idAgent);
    BaseResponse update(Long idPlot, UpdatePlotRequest request);
    BaseResponse listByAgent(Long idAgent);
    BaseResponse delete(Long id);
    Plot getPlot(Long id);
    GetPlotResponse updateToSoldOut( Long idPlot, Client owner);
}
