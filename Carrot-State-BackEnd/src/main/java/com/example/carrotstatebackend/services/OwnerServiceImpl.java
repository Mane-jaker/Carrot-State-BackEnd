package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.CreateSoldRequest;
import com.example.carrotstatebackend.controllers.dtos.response.*;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Owner;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import com.example.carrotstatebackend.repositories.IOwnerRepository;
import com.example.carrotstatebackend.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements IOwnerService {

    @Autowired
    private IOwnerRepository repository;

    @Autowired
    private IAgentService agentService;

    @Autowired
    private ProspectiveBuyerServiceImpl prospectiveBuyerService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private IPlotService plotService;

    @Autowired
    private IPremiseService premiseService;

    @Autowired
    private ISoldService soldService;

    @Override
    public BaseResponse list() {
        return BaseResponse.builder()
                .data(getList())
                .message("List")
                .httpStatus(HttpStatus.FOUND)
                .build();
    }

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(from(findOneAndEnsureExist(id)))
                .message("found")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse createHouseOwner(CreateOwnerRequest request, Long idAgent) {
        Owner owner = from(request, idAgent);
        owner = repository.save(owner);
        GetHouseResponse houseResponse = houseService.updateToSoldOut(request.getIdProperty(), owner);
        CreateSoldRequest soldRequest = new CreateSoldRequest();
        soldRequest.setOwner(owner);
        soldRequest.setAgent(owner.getAgent());
        soldRequest.setHouse(houseService.getHouse(request.getIdProperty()));
        soldService.create(soldRequest);
        return BaseResponse.builder()
                .data(from(owner, houseResponse))
                .message("the owner was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse createPlotOwner(CreateOwnerRequest request, Long idAgent) {
        Owner owner = from(request, idAgent);
        owner = repository.save(owner);
        GetPlotResponse plotResponse = plotService.updateToSoldOut(request.getIdProperty(), owner);
        CreateSoldRequest soldRequest = new CreateSoldRequest();
        soldRequest.setOwner(owner);
        soldRequest.setAgent(owner.getAgent());
        soldRequest.setPlot(plotService.getPlot(request.getIdProperty()));
        soldService.create(soldRequest);
        return BaseResponse.builder()
                .data(from(owner, plotResponse))
                .message("the owner was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse createPremiseOwner(CreateOwnerRequest request, Long idAgent) {
        Owner owner = from(request, idAgent);
        owner = repository.save(owner);
        GetPremiseResponse premiseResponse = premiseService.updateToSoldOut(request.getIdProperty(), owner);
        CreateSoldRequest soldRequest = new CreateSoldRequest();
        soldRequest.setOwner(owner);
        soldRequest.setAgent(owner.getAgent());
        soldRequest.setPremise(premiseService.getPremise(request.getIdProperty()));
        return BaseResponse.builder()
                .data(from(owner, premiseResponse))
                .message("the owner was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public void delete(Long id) {repository.deleteById(id);}

    private Owner findOneAndEnsureExist(Long id){
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private Owner from(CreateOwnerRequest request, Long idAgent){
        ProspectiveBuyer pBuyer = prospectiveBuyerService.getProspectiveBuyer(request.getProspectiveBuyerId());
        Agent agent = agentService.getAgent(idAgent);
        Owner owner = new Owner();
        owner.setName(pBuyer.getName());
        owner.setAgent(agent);
        owner.setContact(pBuyer.getContact());
        return owner;
    }

    private GetOwnerResponse from(Owner owner, Object property){
        GetOwnerResponse response = new GetOwnerResponse();
        response.setId(owner.getId());
        response.setName(owner.getName());
        response.setContact(owner.getContact());
        return response;
    }

    private GetOwnerResponse from(Owner owner){
        GetOwnerResponse response = new GetOwnerResponse();
        response.setId(owner.getId());
        response.setName(owner.getName());
        response.setContact(owner.getContact());
        return response;
    }

    private List<GetOwnerResponse> getList(){
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }
}
