package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.entities.Admin;
import com.example.carrotstatebackend.entities.RealState;

import java.util.Optional;

public interface IAdminService {
    BaseResponse getById(Integer id);
    BaseResponse activateRealState(Long idRealState);
    BaseResponse updateRealStateStatus(Long idRealState, Boolean status);
    Admin getAdmin(String email);
}
