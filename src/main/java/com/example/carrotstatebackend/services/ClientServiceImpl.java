package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateClientRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateClientRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetClientResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.repositories.IClientRepository;
import com.example.carrotstatebackend.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository repository;
    
    @Autowired 
    private IHouseService houseService;

    @Autowired
    private IPlotService plotService;

    @Autowired
    private IPremiseService premiseService;

    @Autowired
    private IClientHouseService prospectiveBuyerHouseService;

    @Autowired
    private IClientPremiseService prospectiveBuyerPremiseService;

    @Autowired
    private IClientPlotService prospectiveBuyerPlotService;

    @Override
    public BaseResponse listByHouse(Long idHouse) {
        List<GetClientResponse> list = prospectiveBuyerHouseService.list(idHouse)
                .stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(list)
                .message("prospective buyers from house: " + idHouse)
                .success(true)
                .httpStatus(HttpStatus.FOUND)
                .build();
    }

    @Override
    public BaseResponse listByPlot(Long idPlot) {
        List<GetClientResponse> list = prospectiveBuyerPlotService.list(idPlot)
                .stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(list)
                .message("prospective buyers from plot: " + idPlot)
                .success(true)
                .httpStatus(HttpStatus.FOUND)
                .build();
    }

    @Override
    public BaseResponse listByPremise(Long idPremise) {
        List<GetClientResponse> list = prospectiveBuyerPremiseService.list(idPremise)
                .stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(list)
                .message("prospective buyers from premise: " + idPremise)
                .success(true)
                .httpStatus(HttpStatus.FOUND)
                .build();
    }

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(from(id))
                .message("found")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse createHouseClient(CreateClientRequest request, Long houseId) {
        Client client = from(request);
        client = repository.save(client);
        House house = houseService.getHouse(houseId);
        prospectiveBuyerHouseService.create(client, house);
        return BaseResponse.builder()
                .data(from(client))
                .message("The prospective buyer was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse createPlotClient(CreateClientRequest request, Long idPlot) {
        Client client = from(request);
        client = repository.save(client);
        Plot plot = plotService.getPlot(idPlot);
        prospectiveBuyerPlotService.create(client, plot);
        return BaseResponse.builder()
                .data(from(client))
                .message("The prospective buyer was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse createPremiseClient(CreateClientRequest request, Long idPremise) {
        Client client = from(request);
        client = repository.save(client);
        Premise premise = premiseService.getPremise(idPremise);
        prospectiveBuyerPremiseService.create(client, premise);
        return BaseResponse.builder()
                .data(from(client))
                .message("The prospective buyer was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateClientRequest request) {
        Client client = findOneAndEnsureExist(id);
        client = update(client, request);
        return BaseResponse.builder()
                .data(from(client))
                .message("the agent was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse deleteHouseClient(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(NotFoundException::new);
        prospectiveBuyerHouseService.delete(client);
        repository.delete(client);
        return BaseResponse.builder()
                .message("the prospective buyer was deleted")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse deletePlotClient(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(NotFoundException::new);
        prospectiveBuyerPlotService.delete(client);
        repository.delete(client);
        return BaseResponse.builder()
                .message("the prospective buyer was deleted")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse deletePremiseClient(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(NotFoundException::new);
        prospectiveBuyerPremiseService.delete(client);
        repository.delete(client);
        return BaseResponse.builder()
                .message("the prospective buyer was deleted")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public Client getClient(Long id){
        return findOneAndEnsureExist(id);
    }

    private Client findOneAndEnsureExist(Long id){
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private Client update(Client client, UpdateClientRequest request){
        client.setName(request.getName());
        client.setContact(request.getContact());
        client.setBudget(request.getBudget());
        return repository.save(client);
    }

    private Client from(CreateClientRequest request){
        Client client = new Client();
        client.setName(request.getName());
        client.setContact(request.getContact());
        client.setBudget(request.getBudget());
        return client;
    }

    private GetClientResponse from(Client client){
        GetClientResponse response = new GetClientResponse();
        response.setId(client.getId());
        response.setName(client.getName());
        response.setContact(client.getContact());
        response.setBudget(client.getBudget());
        return response;
    }

    private GetClientResponse from(Long idProspectiveBuyer){
        return repository.findById(idProspectiveBuyer)
                .map(this::from)
                .orElseThrow(NotFoundException::new);
    }
}
