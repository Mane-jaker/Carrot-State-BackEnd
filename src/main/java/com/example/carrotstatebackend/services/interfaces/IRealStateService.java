package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateRealStateRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetRealStateResponse;
import com.example.carrotstatebackend.entities.RealState;

import java.util.Optional;

public interface IRealStateService {
    BaseResponse list();
    BaseResponse get(Long id);
    BaseResponse create(CreateRealStateRequest request);
    BaseResponse updateCredentials(UpdateCredentialsRequest request, Long idManager);
    BaseResponse updateCommision(Float commision, Long idManager);
    RealState getManagerByCode(Integer managersCode);
    RealState getManager(Long id);
    Optional<RealState> getManager(String email);
    void updateManagerProfile(String fileUrl, Long idManager);
    void delete(Long id);
    GetRealStateResponse getResponse(Long id);
}
