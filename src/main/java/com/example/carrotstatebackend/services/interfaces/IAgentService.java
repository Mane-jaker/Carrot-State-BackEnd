package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetAgentResponse;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Manager;

import java.util.List;

public interface IAgentService{

    BaseResponse listByManager(Long idManager);
    
    BaseResponse get(Long id);

    Agent getAgent(Long id);

    GetAgentResponse getResponse(Long id);

    BaseResponse create(CreateAgentRequest request);

    GetAgentResponse update(Long id, UpdateAgentRequest request);

    void delete(Long id);

    void updateAgentProfile(String fileUrl, Long idManager);

    void updateAgentStatus(Boolean state, Long idAgent);
}
 
