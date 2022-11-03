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
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements IAgentService{

    @Autowired
    private IAgentRepository repository;

    @Override
    public List<GetAgentResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public GetAgentResponse get(Long id) {
        return from(id);
    }

    @Override
    public void updateAgentStatus(Boolean state, Long idAgent){
        Agent agent = findOneAndEnsureExist(idAgent);
        agent.setState(state);
        repository.save(agent);
    }

    @Override
    public void delete(Long id) { repository.deleteById(id); }

    @Override
    public GetAgentResponse create(CreateAgentRequest request) {
        Agent agent = from(request);
        return from(repository.save(agent));
    }

    @Override
    public GetAgentResponse update(Long id, UpdateAgentRequest request) {
        Agent agent = findOneAndEnsureExist(id);
        agent = update(agent, request);
        return from(agent);
    }

    @Override
    public void updateAgentProfile(String fileUrl, Long idAgent) {
        Agent agent = findOneAndEnsureExist(idAgent);
        agent.setProfilePicture(fileUrl);
        repository.save(agent);
    }

    private Agent findOneAndEnsureExist(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    private Agent update(Agent agent, UpdateAgentRequest request){
        agent.setName(request.getName());
        agent.setPassword(request.getPassword());
        agent.setEmail(request.getEmail());
        agent.setNumberOfSales(request.getNumberOfSales());
        agent.setNumberOfProperties(request.getNumberOfPropierties());
        return repository.save(agent);
    }

    private Agent from(CreateAgentRequest request){
        Agent agent = new Agent();
        agent.setName(request.getName());
        agent.setPassword(request.getPassword());
        agent.setEmail(request.getEmail());
        agent.setNumberOfSales(request.getNumberOfSales());
        agent.setNumberOfProperties(request.getNumberOfPropierties());
        return agent;
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
        return response;
    }

    private GetAgentResponse from(Long idAgent){
        return repository.findById(idAgent)
                .map(this::from)
                .orElseThrow(()-> new RuntimeException("Agent no encontrado"));
    }
}
 
