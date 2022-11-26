package com.example.carrotstatebackend.services.interfaces.persons;

import com.example.carrotstatebackend.controllers.dtos.request.persons.BaseAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.persons.UpdateCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetAgentResponse;
import com.example.carrotstatebackend.entities.Agent;

import java.util.Optional;

public interface IAgentService{

    BaseResponse listByRealState(Long idRealState);
    BaseResponse get(Long id);
    BaseResponse create(BaseAgentRequest request);
    BaseResponse update(Long id, BaseAgentRequest request);
    BaseResponse updateCredentials(UpdateCredentialsRequest request, Long idAgent);
    BaseResponse changeStatus(Boolean status, Long idAgent);
    GetAgentResponse getResponse(Long id);
    Agent getAgent(Long id);
    Optional<Agent> getAgent(String email);
    void delete(Long id);
    void updateAgentProfile(String fileUrl, Long idManager);
    void update(Agent agent);
}
 
