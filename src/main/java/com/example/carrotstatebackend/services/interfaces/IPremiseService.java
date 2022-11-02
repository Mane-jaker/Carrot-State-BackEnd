package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetPremiseResponse;
import com.example.carrotstatebackend.entities.Premise;

import java.util.List;

public interface IPremiseService {


    List<GetPremiseResponse> list();

    GetPremiseResponse get(Long id);

    Premise getPremise (Long id);

    void delete(Long id);

    GetPremiseResponse create(CreatePremiseRequest request);

    GetPremiseResponse update(Long id, UpdatePremiseRequest request);
}
