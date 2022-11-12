package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.LoginRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetAgentResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetLoginResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;
import com.example.carrotstatebackend.controllers.exceptions.LoginInvalidException;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Manager;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import com.example.carrotstatebackend.services.interfaces.ILoginService;
import com.example.carrotstatebackend.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IAgentService agentService;

    @Autowired
    private IManagerService managerService;

    @Override
    public BaseResponse managerLogin(LoginRequest loginRequest) {
        Manager manager = managerService.getManager(loginRequest.getEmail())
                .orElseThrow(LoginInvalidException::new);
        if (manager.getPassword().equals(loginRequest.getPassword())) {
            GetLoginResponse response = new GetLoginResponse();
            response.setSuccess(true);
            response.setLoged(from(manager));
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
            GetLoginResponse response = new GetLoginResponse();
            response.setSuccess(true);
            response.setLoged(from(agent));
            return BaseResponse.builder()
                    .data(response)
                    .message("logged")
                    .success(true)
                    .httpStatus(HttpStatus.FOUND).build();
        }
        throw new LoginInvalidException();
    }

    private GetAgentResponse from(Agent agent){
        GetAgentResponse response = new GetAgentResponse();
        response.setId(agent.getId());
        response.setName(agent.getName());
        response.setPassword(agent.getPassword());
        response.setEmail(agent.getEmail());
        response.setNumberOfSales(agent.getNumberOfSales());
        response.setNumberOfPropierties(agent.getNumberOfProperties());
        response.setState(agent.getState());
        response.setManager(from(agent.getManager()));
        return response;
    }

    private GetManagerResponse from(Manager manager){
        GetManagerResponse response = new GetManagerResponse();
        response.setId(manager.getId());
        response.setName(manager.getName());
        response.setMail(manager.getMail());
        response.setPassword(manager.getPassword());
        response.setCommissionAgent(manager.getCommissionAgent());
        response.setManagerCode(manager.getCode().getCode().toString());
        return response;
    }
}
