package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetProspectiveBuyerResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProspectiveBuyerService {

    List<GetProspectiveBuyerResponse> list();

    GetProspectiveBuyerResponse get(Long id);

    void delete(Long id);

    GetProspectiveBuyerResponse create(CreateProspectiveBuyerRequest request);

    GetProspectiveBuyerResponse update(Long id, UpdateProspectiveBuyerRequest request);
}
