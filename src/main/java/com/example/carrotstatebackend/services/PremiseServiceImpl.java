package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetPremiseResponse;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.repositories.IPremiseRepository;
import com.example.carrotstatebackend.services.interfaces.IPremiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremiseServiceImpl implements IPremiseService {

    @Autowired
    private IPremiseRepository repository;

    @Override
    public List<GetPremiseResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public GetPremiseResponse get(Long id) {
        return from(id);
    }

    @Override
    public void delete(Long id) {repository.deleteById(id);

    }

    public Premise getPremise(Long id){
        return findOneAndEnsureExist(id);
    }

    @Override
    public GetPremiseResponse create(CreatePremiseRequest request) {
        Premise premise = from(request);
        return from(repository.save(premise));
    }

    @Override
    public GetPremiseResponse update(Long id, UpdatePremiseRequest request) {

        Premise premise = findOneAndEnsureExist(id);
        premise = update(premise, request);
        return from(premise);

    }

    private Premise findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The Premise does not exist"));
    }

    private GetPremiseResponse from(Premise premise){
        GetPremiseResponse response = new GetPremiseResponse();
       response.setId(premise.getId());
       response.setDescription(premise.getDescription());
       response.setLocation(premise.getLocation());
       response.setName(premise.getName());
       response.setPrice(premise.getPrice());
       response.setSize(premise.getSize());
        return response;
    }

    private  Premise update(Premise premise, UpdatePremiseRequest request){
        premise.setDescription(request.getDescription());
        premise.setPrice(request.getPrice());
        premise.setName(request.getName());
        premise.setLocation(request.getLocation());
        premise.setSize(request.getSize());
        return repository.save(premise);
    }

    private  Premise from(CreatePremiseRequest request){
        Premise premise = new Premise();
        premise.setDescription(request.getDescription());
        premise.setPrice(request.getPrice());
        premise.setName(request.getName());
        premise.setLocation(request.getLocation());
        premise.setSize(request.getSize());
        return premise;
    }

    private GetPremiseResponse from(Long idPremise){
        return repository.findById(idPremise).map(this::from).orElseThrow(()-> new RuntimeException("no ta tu premise papito"));
    }
}
