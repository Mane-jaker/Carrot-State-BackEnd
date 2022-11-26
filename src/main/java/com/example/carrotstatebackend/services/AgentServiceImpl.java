package com.example.carrotstatebackend.services; 
import com.example.carrotstatebackend.controllers.dtos.request.persons.BaseAgentRequest;
import com.example.carrotstatebackend.controllers.dtos.request.persons.UpdateCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetAgentResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.RealState;
import com.example.carrotstatebackend.repositories.IAgentRepository;
import com.example.carrotstatebackend.services.interfaces.persons.IAgentService;
import com.example.carrotstatebackend.services.interfaces.persons.IRealStateService;
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
    private IRealStateService realStateService;

    private final String DEFAULT_PICTURE = "https://conejobucket.s3.us-east-2.amazonaws.com/persons/default/profile/images.jpeg";


    @Override
    public BaseResponse listByRealState(Long idRealState) {
        return BaseResponse.builder()
                .data(getListByRealState(idRealState))
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
    public BaseResponse create(BaseAgentRequest request) {
        Agent agent = from(request);
        RealState realState = realStateService.getManagerByCode(request.getManagerCode());
        agent.setRealState(realState);
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
    public BaseResponse update(Long id, BaseAgentRequest request) {
        Agent agent = findOneAndEnsureExist(id);
        agent = update(agent, request);
        return BaseResponse.builder()
                .data(from(agent))
                .message("The agent was updated")
                .httpStatus(HttpStatus.ACCEPTED)
                .success(true).build();
    }

    @Override
    public BaseResponse updateCredentials(UpdateCredentialsRequest request, Long idAgent) {
        Agent agent = repository.findById(idAgent).orElseThrow(NotFoundException::new);
        agent.setEmail(request.getMail());
        agent.setPassword(request.getPassword());
        return BaseResponse.builder()
                .data(from(repository.save(agent)))
                .message("the agent was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse changeStatus(Boolean status, Long idAgent) {
        Agent agent = repository.findById(idAgent).orElseThrow(NotFoundException::new);
        agent.setState(status);
        return BaseResponse.builder()
                .data(from(repository.save(agent)))
                .message("the agent was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public GetAgentResponse getResponse(Long id){return from(id);}

    @Override
    public Optional<Agent> getAgent(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Agent getAgent(Long id){ return findOneAndEnsureExist(id); }

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
    public void delete(Long id) { repository.deleteById(id); }

    private Agent findOneAndEnsureExist(Long id){
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private Agent update(Agent agent, BaseAgentRequest request){
        agent.setName(request.getName());
        agent.setPassword(request.getPassword());
        agent.setEmail(request.getEmail());
        return repository.save(agent);
    }

    private Agent from(BaseAgentRequest request){
        Agent agent = new Agent();
        agent.setName(request.getName());
        agent.setPassword(request.getPassword());
        agent.setEmail(request.getEmail());
        agent.setProfilePicture(DEFAULT_PICTURE);
        agent.setState(true);
        return agent;
    }

    private GetAgentResponse from(Agent agent){
        return GetAgentResponse.builder()
                .id(agent.getId())
                .name(agent.getName())
                .state(agent.getState())
                .email(agent.getEmail())
                .numberOfSales(agent.getNumberOfSales())
                .profilePicture(agent.getProfilePicture())
                .numberOfProperties(agent.getNumberOfProperties())
                .build();
    }

    private GetAgentResponse from(Long idAgent){
        return repository.findById(idAgent)
                .map(this::from)
                .orElseThrow(NotFoundException::new);
    }

    private List<GetAgentResponse> getListByRealState(Long realStateId){
        return repository
                .findAllByRealState_Id(realStateId)
                .orElseThrow(NotFoundException::new)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }
}
 
