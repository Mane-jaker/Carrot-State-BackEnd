package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetProspectiveBuyerResponse;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import com.example.carrotstatebackend.repositories.IProspectiveBuyerRepository;
import com.example.carrotstatebackend.services.interfaces.IProspectiveBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProspectiveBuyerServiceImpl implements IProspectiveBuyerService {

    @Autowired
    private IProspectiveBuyerRepository repository;

    @Override
    public List<GetProspectiveBuyerResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public GetProspectiveBuyerResponse get(Long id) { return from(id); }

    @Override
    public void delete(Long id) { repository.deleteById(id); }

    @Override
    public GetProspectiveBuyerResponse create(CreateProspectiveBuyerRequest request) {
        ProspectiveBuyer prospectiveBuyer = from(request);
        return from(repository.save(prospectiveBuyer));
    }

    @Override
    public GetProspectiveBuyerResponse update(Long id, UpdateProspectiveBuyerRequest request) {
        ProspectiveBuyer prospectiveBuyer = findOneAndEnsureExist(id);
        prospectiveBuyer = update(prospectiveBuyer, request);
        return from(prospectiveBuyer);
    }

    private ProspectiveBuyer findOneAndEnsureExist(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("The user does not exist"));
    }

    private ProspectiveBuyer update(ProspectiveBuyer prospectiveBuyer, UpdateProspectiveBuyerRequest request){
        prospectiveBuyer.setName(request.getName());
        prospectiveBuyer.setContact(request.getContact());
        prospectiveBuyer.setBudget(request.getBudget());
        return repository.save(prospectiveBuyer);
    }

    private ProspectiveBuyer from(CreateProspectiveBuyerRequest request){
        ProspectiveBuyer prospectiveBuyer = new ProspectiveBuyer();
        prospectiveBuyer.setName(request.getName());
        prospectiveBuyer.setContact(request.getContact());
        prospectiveBuyer.setBudget(request.getBudget());
        return prospectiveBuyer;
    }

    private GetProspectiveBuyerResponse from(ProspectiveBuyer prospectiveBuyer){
        GetProspectiveBuyerResponse response = new GetProspectiveBuyerResponse();
        response.setId(prospectiveBuyer.getId());
        response.setName(prospectiveBuyer.getName());
        response.setContact(prospectiveBuyer.getContact());
        response.setBudget(prospectiveBuyer.getBudget());
        return response;
    }

    private GetProspectiveBuyerResponse from(Long idProspectiveBuyer){
        return repository.findById(idProspectiveBuyer)
                .map(this::from)
                .orElseThrow(()-> new RuntimeException("Prospective buyer no encontrado"));
    }
}
