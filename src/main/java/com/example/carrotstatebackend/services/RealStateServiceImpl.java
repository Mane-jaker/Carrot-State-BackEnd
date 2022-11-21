package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateRealStateRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetRealStateResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.RealState;
import com.example.carrotstatebackend.entities.RealStateCode;
import com.example.carrotstatebackend.repositories.IRealStateRepository;
import com.example.carrotstatebackend.services.interfaces.IRealStateService;
import com.example.carrotstatebackend.services.interfaces.IRealStateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RealStateServiceImpl implements IRealStateService {

    @Autowired
    private IRealStateRepository repository;

    @Override
    public void updateManagerProfile(String fileUrl, Long idManager) {
        RealState realState = findOneAndEnsureExist(idManager);
        realState.setProfilePicture(fileUrl);
        repository.save(realState);
    }

    @Override
    public BaseResponse list() {
        return BaseResponse.builder()
                .data(getList())
                .message("managers list")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetRealStateResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("the manager was found")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    public GetRealStateResponse getResponse(Long id){
        return from(id);
    }

    @Override
    public RealState getManagerByCode(String managerCode) {
        return repository.findByCode_Code(managerCode).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BaseResponse create(CreateRealStateRequest request) {
        RealState realState = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(realState)))
                .message("the manager was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse updateCredentials(UpdateCredentialsRequest request, Long idManager) {
        RealState realState = repository.findById(idManager)
                .orElseThrow(NotFoundException::new);
        realState.setEmail(request.getMail());
        realState.setPassword(request.getPassword());
        return BaseResponse.builder()
                .data(from(repository.save(realState)))
                .message("the agent was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse updateStatus(Boolean status, Long id) {
        RealState realState = findOneAndEnsureExist(id);
        realState.setStatus(status);
        return BaseResponse.builder()
                .data(from(repository.save(realState)))
                .message("the real state was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED)
                .build();
    }

    @Override
    public BaseResponse updateCommision(Float commision, Long idManager) {
        RealState realState = repository
                .findById(idManager)
                .orElseThrow(NotFoundException::new);
        realState.setCommissionAgent(commision);
        return BaseResponse.builder()
                .data(from(repository.save(realState)))
                .message("the agent was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse activate(Long idRealState, RealStateCode code) {
        RealState realState = findOneAndEnsureExist(idRealState);
        if (realState.getStatus()) throw new NotFoundException();
        realState.setStatus(true);
        realState.setCode(code);
        return BaseResponse.builder()
                .data(from(realState))
                .message("the realState was activated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    public RealState getManager(Long id){
        return findOneAndEnsureExist(id);
    }

    @Override
    public RealState getRealState(String email) {
        return repository.findByEmail(email).orElseThrow(NotFoundException::new);
    }

    private RealState findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private RealState from(CreateRealStateRequest request){
        RealState realState = new RealState();
        realState.setName(request.getName());
        realState.setEmail(request.getEmail());
        realState.setPassword(request.getPassword());
        realState.setCommissionAgent(request.getCommissionAgent());
        realState.setStatus(false);
        return realState;
    }

    private GetRealStateResponse from(RealState realState){
        GetRealStateResponse response = new GetRealStateResponse();
        response.setId(realState.getId());
        response.setName(realState.getName());
        response.setEmail(realState.getEmail());
        response.setPassword(realState.getPassword());
        response.setCommissionAgent(realState.getCommissionAgent());
        response.setStatus(realState.getStatus());
        response.setRealStateCode(realState.getCode().getCode());
        return response;
    }

    private GetRealStateResponse from(Long idManger){
        return repository.findById(idManger)
                .map(this::from).orElseThrow(NotFoundException::new);
    }

    private List<GetRealStateResponse> getList(){
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }
}
