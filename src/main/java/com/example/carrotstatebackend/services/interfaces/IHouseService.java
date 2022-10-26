package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetHouseResponse;
import com.example.carrotstatebackend.entities.House;

import java.util.List;

public interface IHouseService {

    List<GetHouseResponse> list();

    GetHouseResponse get(Long id);

    House getHouse(Long id);

    void delete(Long id);

    GetHouseResponse create(CreateHouseRequest request);

    GetHouseResponse update(Long id, UpdateHouseRequest request);
}
