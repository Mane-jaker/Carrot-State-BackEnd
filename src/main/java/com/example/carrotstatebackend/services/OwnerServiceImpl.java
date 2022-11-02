package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetOwnerResponse;
import com.example.carrotstatebackend.entities.Owner;
import com.example.carrotstatebackend.repositories.IOwnerRepository;
import com.example.carrotstatebackend.services.interfaces.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements IOwnerService {

    @Autowired
    private IOwnerRepository repository;

    @Override
    public List<GetOwnerResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public GetOwnerResponse get(Long id) { return from(id); }

    @Override
    public void delete(Long id) { repository.deleteById(id); }

    @Override
    public GetOwnerResponse create(CreateOwnerRequest request) {
        Owner owner = from(request);
        return from(repository.save(owner));
    }

    @Override
    public GetOwnerResponse update(Long id, UpdateOwnerRequest request) {
        Owner owner = findOneAndEnsureExist(id);
        owner = update(owner, request);
        return from(owner);
    }

    private Owner findOneAndEnsureExist(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    private Owner update(Owner owner, UpdateOwnerRequest request){
        owner.setName(request.getName());
        owner.setContact(request.getContact());
        owner.setProperty(request.getPropierty());
        return repository.save(owner);
    }

    private Owner from(CreateOwnerRequest request){
        Owner owner = new Owner();
        owner.setName(request.getName());
        owner.setContact(request.getContact());
        owner.setProperty(request.getPropierty());
        return owner;
    }
    private GetOwnerResponse from(Owner owner){
        GetOwnerResponse response = new GetOwnerResponse();
        response.setId(owner.getId());
        response.setName(owner.getName());
        response.setContact(owner.getContact());
        response.setPropierty(owner.getProperty());
        return response;
    }

    private GetOwnerResponse from(Long idOwner){
        return repository.findById(idOwner)
                .map(this::from)
                .orElseThrow(()-> new  RuntimeException("Owner no encontrado"));
    }
}
