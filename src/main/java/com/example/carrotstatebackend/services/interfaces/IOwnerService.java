package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetOwnerResponse;

import java.util.List;

public interface IOwnerService {

    List<GetOwnerResponse> list();

    GetOwnerResponse get(Long id);

    void delete(Long id);

    GetOwnerResponse create(CreateOwnerRequest request);

    GetOwnerResponse update(Long id, UpdateOwnerRequest request);
}
