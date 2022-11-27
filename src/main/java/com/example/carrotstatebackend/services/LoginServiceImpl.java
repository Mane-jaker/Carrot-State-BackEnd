package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.LoginRequest;
import com.example.carrotstatebackend.controllers.dtos.response.*;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetAdminResponse;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetAgentResponse;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetClientResponse;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetRealStateResponse;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.exceptions.LoginInvalidException;
import com.example.carrotstatebackend.entities.Admin;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.RealState;
import com.example.carrotstatebackend.services.interfaces.persons.IAdminService;
import com.example.carrotstatebackend.services.interfaces.persons.IAgentService;
import com.example.carrotstatebackend.services.interfaces.ILoginService;
import com.example.carrotstatebackend.services.interfaces.persons.IClientService;
import com.example.carrotstatebackend.services.interfaces.persons.IRealStateService;
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

    @Autowired
    private IClientService clientService;


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
        Agent agent = agentService.getAgent(loginRequest.getEmail())
                .orElseThrow(LoginInvalidException::new);
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

    @Override
    public BaseResponse clientLogin(LoginRequest loginRequest) {
        Client client = clientService.getClient(loginRequest.getEmail())
                .orElseThrow(LoginInvalidException::new);
        if (client.getPassword().equals(loginRequest.getPassword())){
            GetLoginResponse response = GetLoginResponse.builder()
                    .success(true)
                    .logged(from(client)).build();
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
                .profilePicture(agent.getProfilePicture())
                .numberOfProperties(agent.getNumberOfProperties())
                .numberOfSales(agent.getNumberOfSales()).build();
    }

    private GetRealStateResponse from(RealState realState){
        GetRealStateResponse response = new GetRealStateResponse();
        response.setId(realState.getId());
        response.setName(realState.getName());
        response.setEmail(realState.getEmail());
        response.setCommissionAgent(realState.getCommissionAgent());
        if (realState.getStatus()) response.setRealStateCode(realState.getCode().getCode());
        response.setStatus(realState.getStatus());
        response.setProfilePicture(realState.getProfilePicture());
        return response;
    }

    private GetClientResponse from(Client client){
        return GetClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .contact(client.getContact())
                .budget(client.getBudget()).build();
    }
}
