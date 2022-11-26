package com.example.carrotstatebackend.services.interfaces.persons;

import com.example.carrotstatebackend.controllers.dtos.request.persons.BaseClientRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.entities.Client;


public interface IClientService {
    BaseResponse listByHouse(Long idHouse);
    BaseResponse listByPlot(Long idPlot);
    BaseResponse listByPremise(Long idPremise);
    BaseResponse get(Long id);
    BaseResponse createHouseClient(BaseClientRequest request, Long idHouse);
    BaseResponse createPlotClient(BaseClientRequest request, Long idPlot);
    BaseResponse createPremiseClient(BaseClientRequest request, Long idPremise);
    BaseResponse update(Long id, BaseClientRequest request);
    BaseResponse deleteHouseClient(Long id);
    BaseResponse deletePlotClient(Long id);
    BaseResponse deletePremiseClient(Long id);
    Client getClient(Long id);
}
