package com.example.carrotstatebackend.services.interfaces;


import com.example.carrotstatebackend.controllers.dtos.request.CreatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetPremiseResponse;
import com.example.carrotstatebackend.entities.Owner;
import com.example.carrotstatebackend.entities.Premise;


public interface IPremiseService {
    BaseResponse get(Long id);
    BaseResponse create(CreatePremiseRequest request, Long idAgent);
    BaseResponse update(Long idPremise, UpdatePremiseRequest request);
    BaseResponse listByAgent(Long idAgent);
    BaseResponse delete(Long id);
    Premise getPremise (Long id);
    GetPremiseResponse updateToSoldOut(Long id, Owner owner);
}
