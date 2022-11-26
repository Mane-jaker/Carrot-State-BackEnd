package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.persons.BaseRealStateRequest;
import com.example.carrotstatebackend.controllers.dtos.request.persons.UpdateCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetRealStateResponse;
import com.example.carrotstatebackend.exceptions.InvalidActivationException;
import com.example.carrotstatebackend.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.Admin;
import com.example.carrotstatebackend.entities.RealState;
import com.example.carrotstatebackend.repositories.persons.IRealStateRepository;
import com.example.carrotstatebackend.services.interfaces.persons.IAdminService;
import com.example.carrotstatebackend.services.interfaces.persons.IRealStateService;
import com.example.carrotstatebackend.services.interfaces.persons.IRealStateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RealStateServiceImpl implements IRealStateService {

    @Autowired
    private IRealStateRepository repository;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IRealStateCodeService realStateCodeService;

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
    public BaseResponse create(BaseRealStateRequest request) {
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
    public BaseResponse activate(Long idRealState) {
        RealState realState = findOneAndEnsureExist(idRealState);
        if (realState.getStatus()) throw new InvalidActivationException();
        realState.setStatus(true);
        realState.setCode(realStateCodeService.GenerateManagerCode());
        return BaseResponse.builder()
                .data(from(repository.save(realState)))
                .message("the realState was activated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public RealState getRealState(String email) {
        return repository.findByEmail(email).orElseThrow(NotFoundException::new);
    }

    private RealState findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private RealState from(BaseRealStateRequest request){
        Admin admin = adminService.getAdmin(1);
        RealState realState = new RealState();
        realState.setName(request.getName());
        realState.setEmail(request.getEmail());
        realState.setPassword(request.getPassword());
        realState.setCommissionAgent(request.getCommissionAgent());
        realState.setProfilePicture("https://conejobucket.s3.us-east-2.amazonaws.com/persons/default/profile/images.jpeg");
        realState.setStatus(false);
        realState.setAdmin(admin);
        return realState;
    }

    private GetRealStateResponse from(RealState realState){
        GetRealStateResponse response = new GetRealStateResponse();
        response.setId(realState.getId());
        response.setName(realState.getName());
        response.setEmail(realState.getEmail());
        response.setCommissionAgent(realState.getCommissionAgent());
        response.setStatus(realState.getStatus());
        if (realState.getCode() != null) response.setRealStateCode(realState.getCode().getCode());
        response.setProfilePicture(realState.getProfilePicture());
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
