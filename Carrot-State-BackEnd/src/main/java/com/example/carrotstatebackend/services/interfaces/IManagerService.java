package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateManagerCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;
import com.example.carrotstatebackend.entities.Manager;

import java.util.List;
import java.util.Optional;

public interface IManagerService {
    BaseResponse list();
    BaseResponse get(Long id);
    BaseResponse create(CreateManagerRequest request);
    BaseResponse updateCredentials(UpdateManagerCredentialsRequest request, Long idManager);
    BaseResponse updateCommision(Float commision, Long idManager);
    Manager getManagerByCode(Long managersCode);
    Manager getManager(Long id);
    Optional<Manager> getManager(String email);
    void updateManagerProfile(String fileUrl, Long idManager);
    void delete(Long id);
    GetManagerResponse getResponse(Long id);
}
