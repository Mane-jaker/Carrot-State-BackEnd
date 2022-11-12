package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetHouseResponse;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Owner;


public interface IHouseService {
    BaseResponse get(Long id);
    BaseResponse create(CreateHouseRequest request, Long idAgent);
    BaseResponse update(Long idHouse, UpdateHouseRequest request);
    BaseResponse listByAgent(Long idAgent);
    BaseResponse delete(Long id);
    GetHouseResponse updateToSoldOut(Long id, Owner owner);
    House getHouse(Long id);
}
