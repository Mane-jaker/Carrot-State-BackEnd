package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetHouseResponse;

import java.util.List;

public interface IHouseService {

    void updateHouseProfile(String fileUrl, Long idHouse);

    List<GetHouseResponse> list();

    GetHouseResponse get(Long id);

    void delete(Long id);

    GetHouseResponse create(CreateManagerRequest request);

    GetHouseResponse update(Long id, UpdateManagerRequest request);
}
