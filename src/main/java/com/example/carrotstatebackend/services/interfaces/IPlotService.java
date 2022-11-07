package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetPlotResponse;
import com.example.carrotstatebackend.entities.Owner;
import com.example.carrotstatebackend.entities.Plot;

import java.util.List;

public interface IPlotService {

    BaseResponse listByAgent(Long id);

    BaseResponse get(Long id);

    Plot getPlot(Long id);

    void delete(Long id);

    BaseResponse create(CreatePlotRequest request, Long idAgent);

    GetPlotResponse update(Long id, UpdatePlotRequest request);

    GetPlotResponse updateToSoldOut( Long idPlot, Owner owner);
}
