package com.example.carrotstatebackend.services.interfaces.persons;

import com.example.carrotstatebackend.controllers.dtos.request.persons.BaseRealStateRequest;
import com.example.carrotstatebackend.controllers.dtos.request.persons.UpdateCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetRealStateResponse;
import com.example.carrotstatebackend.entities.RealState;

public interface IRealStateService {
    BaseResponse list();
    BaseResponse get(Long id);
    BaseResponse create(BaseRealStateRequest request);
    BaseResponse updateCredentials(UpdateCredentialsRequest request, Long idManager);
    BaseResponse updateStatus(Boolean status, Long id);
    BaseResponse updateCommision(Float commision, Long idManager);
    BaseResponse activate(Long idRealState);
    RealState getManagerByCode(String managersCode);
    RealState getRealState(String email);
    void updateManagerProfile(String fileUrl, Long idManager);
    void delete(Long id);
    GetRealStateResponse getResponse(Long id);
}
