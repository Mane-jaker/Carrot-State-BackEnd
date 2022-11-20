package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.Plot;

import java.util.List;

public interface IClientPlotService {
    List<Client> list(Long idPlot);
    void create(Client client, Plot plot);
    void delete(Client client);
}
