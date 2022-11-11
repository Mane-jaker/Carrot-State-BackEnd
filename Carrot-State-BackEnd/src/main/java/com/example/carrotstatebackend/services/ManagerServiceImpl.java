package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateManagerCredentialsRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.Manager;
import com.example.carrotstatebackend.repositories.IManagerRepository;
import com.example.carrotstatebackend.services.interfaces.IManagerService;
import com.example.carrotstatebackend.services.interfaces.IManagersCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
@Service
public class ManagerServiceImpl implements IManagerService {

    @Autowired
    private IManagerRepository repository;

    @Autowired
    private IManagersCode managersCodeService;

    @Override
    public void updateManagerProfile(String fileUrl, Long idManager) {
        Manager manager = findOneAndEnsureExist(idManager);
        manager.setProfilePicture(fileUrl);
        repository.save(manager);
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
        GetManagerResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("the manager was found")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    public GetManagerResponse getResponse(Long id){
        return from(id);
    }

    @Override
    public Manager getManagerByCode(Long managerCode) {
        return repository.findByCodeCode(managerCode).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BaseResponse create(CreateManagerRequest request) {
        Manager manager = from(request);
        manager.setCode(managersCodeService.GenerateManagerCode());
        GetManagerResponse response = from(repository.save(manager));
        return BaseResponse.builder()
                .data(response)
                .message("the manager was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse updateCredentials(UpdateManagerCredentialsRequest request, Long idManager) {
        Manager manager = repository.findById(idManager).orElseThrow(NotFoundException::new);
        manager.setMail(request.getMail());
        manager.setPassword(request.getPassword());
        return BaseResponse.builder()
                .data(from(repository.save(manager)))
                .message("the agent was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse updateCommision(Float commision, Long idManager) {
        Manager manager = repository.findById(idManager).orElseThrow(NotFoundException::new);
        manager.setCommissionAgent(commision);
        return BaseResponse.builder()
                .data(from(repository.save(manager)))
                .message("the agent was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    public Manager getManager(Long id){
        return findOneAndEnsureExist(id);
    }

    @Override
    public Optional<Manager> getManager(String email) {
        return repository.findByMail(email);
    }

    private Manager findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private  Manager from(CreateManagerRequest request){
        Manager manager = new Manager();
        manager.setName(request.getName());
        manager.setMail(request.getEmail());
        manager.setPassword(request.getPassword());
        manager.setCommissionAgent(request.getCommissionAgent());
        return manager;
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

    private GetManagerResponse from(Long idManger){
        return repository.findById(idManger)
                .map(this::from).orElseThrow(NotFoundException::new);
    }

    private List<GetManagerResponse> getList(){
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }
}
