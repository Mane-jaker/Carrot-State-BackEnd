package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetHouseResponse;

import java.util.List;

public interface IHouseService {

    void updateHouseProfile(String fileUrl, Long idHouse);

    List<GetHouseResponse> list();

    GetHouseResponse get(Long id);

    void delete(Long id);

    GetHouseResponse create(CreateHouseRequest request);

    GetHouseResponse update(Long id, UpdateHouseRequest request);
}
