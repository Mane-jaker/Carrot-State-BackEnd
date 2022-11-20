package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.LoginRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetAgentResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetLoginResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetRealStateResponse;
import com.example.carrotstatebackend.controllers.exceptions.LoginInvalidException;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.RealState;
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

    @Override
    public BaseResponse managerLogin(LoginRequest loginRequest) {
        RealState realState = managerService.getManager(loginRequest.getEmail())
                .orElseThrow(LoginInvalidException::new);
        if (realState.getPassword().equals(loginRequest.getPassword())) {
            GetLoginResponse response = new GetLoginResponse();
            response.setSuccess(true);
            response.setLogged(from(realState));
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
            response.setLogged(from(agent));
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
        response.setNumberOfProperties(agent.getNumberOfProperties());
        response.setState(agent.getState());
        response.setRealState(from(agent.getRealState()));
        return response;
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
