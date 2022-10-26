package com.example.carrotstatebackend.services; 
import com.example.carrotstatebackend.controllers.dtos.request.CreateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetAgentResponse;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.repositories.IAgentRepository;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements IAgentService{

    @Autowired
    private IAgentRepository repository;

    @Override
    public List<GetAgentResponse> list() {
        return null;
    }

    @Override
    public GetAgentResponse get(Long id) {
        return null;
    }

    @Override
    public Agent getAgent(Long id){
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public GetAgentResponse create(CreateAgentRequest request) {
        return null;
    }

    @Override
    public GetAgentResponse update(Long id, UpdateAgentRequest request) {
        return null;
    }

    @Override
    public void updateAgentProfile(String fileUrl, Long idManager) {

    }
}
 
