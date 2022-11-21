package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.LoginRequest;
import com.example.carrotstatebackend.controllers.dtos.response.*;
import com.example.carrotstatebackend.controllers.exceptions.LoginInvalidException;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.Admin;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.RealState;
import com.example.carrotstatebackend.services.interfaces.IAdminService;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import com.example.carrotstatebackend.services.interfaces.ILoginService;
import com.example.carrotstatebackend.services.interfaces.IRealStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IAgentService agentService;

    @Autowired
    private IRealStateService managerService;

    @Autowired
    private IAdminService adminService;


    @Override
    public BaseResponse adminLogin(LoginRequest loginRequest) {
        Admin admin = adminService.getAdmin(loginRequest.getEmail());
        if (admin.getPassword().equals(loginRequest.getPassword())){
            GetLoginResponse response = GetLoginResponse.builder()
                    .success(true)
                    .logged(from(admin)).build();
            return BaseResponse.builder()
                    .data(response)
                    .message("logged")
                    .success(true)
                    .httpStatus(HttpStatus.FOUND).build();
        }
        throw new LoginInvalidException();
    }

    @Override
    public BaseResponse managerLogin(LoginRequest loginRequest) {
        RealState realState = managerService.getRealState(loginRequest.getEmail());
        if (realState.getPassword().equals(loginRequest.getPassword())) {
            GetLoginResponse response = GetLoginResponse.builder()
                    .success(true)
                    .logged(from(realState)).build();
            return BaseResponse.builder()
                    .data(response)
                    .message("logged")
                    .success(true)
                    .httpStatus(HttpStatus.FOUND).build();
        }
        throw new LoginInvalidException();
    }

    @Override
    public BaseResponse agentLogin(LoginRequest loginRequest) {
        Agent agent = agentService.getAgent(loginRequest.getEmail());
        if (agent.getPassword().equals(loginRequest.getPassword())){
            GetLoginResponse response = GetLoginResponse.builder()
                    .success(true)
                    .logged(from(agent)).build();
            return BaseResponse.builder()
                    .data(response)
                    .message("logged")
                    .success(true)
                    .httpStatus(HttpStatus.FOUND).build();
        }
        throw new LoginInvalidException();
    }

    private GetAdminResponse from(Admin admin){
        return GetAdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail()).build();
    }

    private GetAgentResponse from(Agent agent){
        return GetAgentResponse.builder()
                .id(agent.getId())
                .name(agent.getName())
                .state(agent.getState())
                .email(agent.getEmail())
                .password(agent.getPassword())
                .realState(from(agent.getRealState()))
                .numberOfProperties(agent.getNumberOfProperties())
                .numberOfSales(agent.getNumberOfSales()).build();
    }

    private GetRealStateResponse from(RealState realState){
        GetRealStateResponse response = new GetRealStateResponse();
        response.setId(realState.getId());
        response.setName(realState.getName());
        response.setEmail(realState.getEmail());
        response.setPassword(realState.getPassword());
        response.setCommissionAgent(realState.getCommissionAgent());
        response.setRealStateCode(realState.getCode().getCode().toString());
        return response;
    }
}
