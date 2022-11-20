package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateClientRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateClientRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.entities.Client;


public interface IClientService {
    BaseResponse listByHouse(Long idHouse);
    BaseResponse listByPlot(Long idPlot);
    BaseResponse listByPremise(Long idPremise);
    BaseResponse get(Long id);
    BaseResponse createHouseClient(CreateClientRequest request, Long idHouse);
    BaseResponse createPlotClient(CreateClientRequest request, Long idPlot);
    BaseResponse createPremiseClient(CreateClientRequest request, Long idPremise);
    BaseResponse update(Long id, UpdateClientRequest request);
    BaseResponse deleteHouseClient(Long id);
    BaseResponse deletePlotClient(Long id);
    BaseResponse deletePremiseClient(Long id);
    Client getClient(Long id);
}
