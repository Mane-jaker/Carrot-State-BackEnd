package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetAgentResponse;
import com.example.carrotstatebackend.entities.Agent;

import java.util.Optional;

public interface IAgentService{

    BaseResponse listByRealState(Long idRealState);
    BaseResponse get(Long id);
    BaseResponse create(CreateAgentRequest request);
    BaseResponse update(Long idAgent, UpdateAgentRequest request);
    BaseResponse updateCredentials(UpdateCredentialsRequest request, Long idAgent);
    BaseResponse changeStatus(Boolean status, Long idAgent);
    GetAgentResponse getResponse(Long id);
    Agent getAgent(Long id);
    Agent getAgent(String email);
    void delete(Long id);
    void updateAgentProfile(String fileUrl, Long idManager);
    void update(Agent agent);
}
 
