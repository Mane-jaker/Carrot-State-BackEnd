package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;

import java.util.List;

public interface IProspectiveBuyerPlotService {
    List<ProspectiveBuyer> list(Long idPlot);
    void create(ProspectiveBuyer prospectiveBuyer, Plot plot);
}
