package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.entities.Owner;

public interface IOwnerService {
    BaseResponse list();
    BaseResponse get(Long id);
    BaseResponse createHouseOwner(CreateOwnerRequest request, Long idAgent);
    BaseResponse createPlotOwner(CreateOwnerRequest request, Long idAgent);
    BaseResponse createPremiseOwner(CreateOwnerRequest request, Long idAgent);
    BaseResponse listOwnerHouses(Long idOwner);
    BaseResponse listOwnerPlots(Long idOwner);
    BaseResponse listOwnerPremises(Long idOwner);
    Owner getOwner(Long idOwner);
    void delete(Long id);
}
