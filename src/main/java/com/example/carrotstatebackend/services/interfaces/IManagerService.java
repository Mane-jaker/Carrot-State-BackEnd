package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;

import java.util.List;

public interface IManagerService {

    void updateManagerProfile(String fileUrl, Long idManager);

    List<GetManagerResponse> list();

    GetManagerResponse get(Long id);

    void delete(Long id);

    GetManagerResponse create(CreateManagerRequest request);

    GetManagerResponse update(Long id, UpdateManagerRequest request);

}
