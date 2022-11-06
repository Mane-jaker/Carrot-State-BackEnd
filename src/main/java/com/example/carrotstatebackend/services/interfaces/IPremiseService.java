package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.CreatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetPlotResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetPremiseResponse;
import com.example.carrotstatebackend.entities.Owner;
import com.example.carrotstatebackend.entities.Premise;


public interface IPremiseService {

    BaseResponse listByAgent(Long idAgent);

    BaseResponse get(Long id);

    Premise getPremise (Long id);

    void delete(Long id);

    BaseResponse create(CreatePremiseRequest request, Long idAgent);

    GetPremiseResponse update(Long id, UpdatePremiseRequest request);

    GetPremiseResponse updateToSoldOut(Long id, Owner owner);
}
