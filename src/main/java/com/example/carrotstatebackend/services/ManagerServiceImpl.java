package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateManagerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetManagerResponse;
import com.example.carrotstatebackend.entities.Manager;
import com.example.carrotstatebackend.repositories.IManagerRepository;
import com.example.carrotstatebackend.services.interfaces.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private IManagerRepository repository;


    @Override
    public void updateManagerProfile(String fileUrl, Long idManager) {
        Manager manager = findOneAndEnsureExist(idManager);
        manager.setProfilePicture(fileUrl);
        repository.save(manager);
    }

    @Override
    public List<GetManagerResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public GetManagerResponse get(Long id) {
        return from(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public GetManagerResponse create(CreateManagerRequest request) {
        Manager manager = from(request);
        return from(repository.save(manager));
    }

    @Override
    public GetManagerResponse update(Long id, UpdateManagerRequest request) {
        Manager manager = findOneAndEnsureExist(id);
        manager = update(manager, request);
        return from(manager);
    }

    private Manager findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    private  Manager update(Manager manager,UpdateManagerRequest request){
        manager.setName(request.getName());
        manager.setMail(request.getMail());
        manager.setManagerCode(request.getManagerCode());
        manager.setPassword(request.getPassword());
        manager.setCommissionAgent(request.getCommissionAgent());
        return repository.save(manager);
    }

    private  Manager from(CreateManagerRequest request){
        Manager manager = new Manager();
        manager.setName(request.getName());
        manager.setMail(request.getMail());
        manager.setManagerCode(request.getManagerCode());
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
        response.setManagerCode(manager.getManagerCode());
        response.setCommissionAgent(manager.getCommissionAgent());
        return response;
    }
    private GetManagerResponse from(Long idManger){
        return repository.findById(idManger)
                .map(this::from)
                .orElseThrow(()-> new  RuntimeException("No ta tu Manager Papito"));
    }
}
