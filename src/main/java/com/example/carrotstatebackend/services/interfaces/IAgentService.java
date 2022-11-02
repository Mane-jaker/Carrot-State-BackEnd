package com.example.carrotstatebackend.services.interfaces;

import com.example.carrotstatebackend.controllers.dtos.request.CreateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetAgentResponse;
import com.example.carrotstatebackend.entities.Agent;

import java.util.List;

public interface IAgentService{

    List<GetAgentResponse> list();

    GetAgentResponse get(Long id);

    void delete(Long id);

    GetAgentResponse create(CreateAgentRequest request);

    GetAgentResponse update(Long id, UpdateAgentRequest request);

    void updateAgentProfile(String fileUrl, Long idManager);

    void updateAgentStatus(Boolean state, Long idAgent);
}
 
