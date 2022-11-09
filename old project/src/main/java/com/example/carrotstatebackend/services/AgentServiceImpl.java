package com.example.carrotstatebackend.services; 
import com.example.carrotstatebackend.controllers.dtos.request.CreateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetAgentResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Manager;
import com.example.carrotstatebackend.repositories.IAgentRepository;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import com.example.carrotstatebackend.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements IAgentService{

    @Autowired
    private IAgentRepository repository;

    @Autowired
    private IManagerService managerService;


    @Override
    public BaseResponse listByManager(Long managerId) {
        return BaseResponse.builder()
                .data(getListByManager(managerId))
                .message("List")
                .success(true)
                .httpStatus(HttpStatus.FOUND)
                .build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetAgentResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("the agent was find")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public void updateAgentStatus(Boolean state, Long idAgent){
        Agent agent = findOneAndEnsureExist(idAgent);
        agent.setState(state);
        repository.save(agent);
    }

    @Override
    public void delete(Long id) { repository.deleteById(id); }

    public GetAgentResponse getResponse(Long id){

        return from(id);
    }

    @Override
    public BaseResponse create(CreateAgentRequest request) {
        Agent agent = from(request);
        Manager manager = managerService.getManagerByCode(request.getManagerCode());
        agent.setManager(manager);
        agent.setNumberOfProperties(0);
        agent.setNumberOfSales(0);
        GetAgentResponse response = from(repository.save(agent));
        return BaseResponse.builder()
                .data(response)
                .message("the agent was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateAgentRequest request) {
        Agent agent = findOneAndEnsureExist(id);
        agent = update(agent, request);
        return BaseResponse.builder()
                .data(from(agent))
                .message("The agent was updated")
                .httpStatus(HttpStatus.ACCEPTED)
                .success(true).build();
    }

    @Override
    public Optional<Agent> getAgent(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void updateAgentProfile(String fileUrl, Long idAgent) {
        Agent agent = findOneAndEnsureExist(idAgent);
        agent.setProfilePicture(fileUrl);
        repository.save(agent);
    }

    @Override
    public void update(Agent agent) {
        repository.save(agent);
    }

    @Override
    public Agent getAgent(Long id){ return findOneAndEnsureExist(id); }

    private Agent findOneAndEnsureExist(Long id){
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private Agent update(Agent agent, UpdateAgentRequest request){
        agent.setName(request.getName());
        agent.setPassword(request.getPassword());
        agent.setEmail(request.getEmail());
        return repository.save(agent);
    }
//quite esos parametros xd
    private Agent from(CreateAgentRequest request){
        Agent agent = new Agent();
        agent.setName(request.getName());
        agent.setPassword(request.getPassword());
        agent.setEmail(request.getEmail());
        agent.setState(true);
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

    private GetAgentResponse from(Long idAgent){
        return repository.findById(idAgent)
                .map(this::from)
                .orElseThrow(NotFoundException::new);
    }

    private List<GetAgentResponse> getListByManager(Long idManger){
        Manager manager = managerService.getManager(idManger);
        return repository
                .findAllByManager_Id(idManger)
                .orElseThrow(NotFoundException::new)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }
}
 
