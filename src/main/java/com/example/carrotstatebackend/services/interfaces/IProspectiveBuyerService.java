package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;


public interface IProspectiveBuyerService {
    BaseResponse listByHouse(Long idHouse);
    BaseResponse listByPlot(Long idPlot);
    BaseResponse listByPremise(Long idPremise);
    BaseResponse get(Long id);
    BaseResponse createHouseProspectiveBuyer(CreateProspectiveBuyerRequest request, Long idHouse);
    BaseResponse createPlotProspectiveBuyer(CreateProspectiveBuyerRequest request, Long idPlot);
    BaseResponse createPremiseProspectiveBuyer(CreateProspectiveBuyerRequest request, Long idPremise);
    BaseResponse update(Long id, UpdateProspectiveBuyerRequest request);
    BaseResponse deleteHouseProspective(Long id);
    BaseResponse deletePlotProspective(Long id);
    BaseResponse deletePremiseProspective(Long id);
    ProspectiveBuyer getProspectiveBuyer(Long id);
}
