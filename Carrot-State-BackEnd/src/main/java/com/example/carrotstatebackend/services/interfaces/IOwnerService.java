package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;

public interface IOwnerService {

    BaseResponse list();

    BaseResponse get(Long id);

    BaseResponse createHouseOwner(CreateOwnerRequest request, Long idAgent);

    BaseResponse createPlotOwner(CreateOwnerRequest request, Long idAgent);

    BaseResponse createPremiseOwner(CreateOwnerRequest request, Long idAgent);

    void delete(Long id);
}
