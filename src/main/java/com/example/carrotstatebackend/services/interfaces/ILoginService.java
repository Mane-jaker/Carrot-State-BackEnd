package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.LoginRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;

public interface ILoginService {
    BaseResponse adminLogin(LoginRequest loginRequest);
    BaseResponse managerLogin(LoginRequest loginRequest);
    BaseResponse agentLogin(LoginRequest loginRequest);
    BaseResponse clientLogin(LoginRequest loginRequest);
}
