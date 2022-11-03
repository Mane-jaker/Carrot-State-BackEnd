package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetPlotResponse;
import com.example.carrotstatebackend.entities.Plot;

import java.util.List;

public interface IPlotService {

    List<GetPlotResponse> list();

    GetPlotResponse get(Long id);

    Plot getPlot(Long id);

    void delete(Long id);

    GetPlotResponse create(CreatePlotRequest request);

    GetPlotResponse update(Long id, UpdatePlotRequest request);
}
