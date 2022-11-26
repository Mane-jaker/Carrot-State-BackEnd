package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.persons.BaseClientRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetClientResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.repositories.IClientRepository;
import com.example.carrotstatebackend.services.interfaces.persons.IClientService;
import com.example.carrotstatebackend.services.interfaces.pivtos.IBaseClientPropertyService;
import com.example.carrotstatebackend.services.interfaces.properties.IBasePropertyService;
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
    private IBasePropertyService<House> houseService;

    @Autowired
    private IBasePropertyService<Plot> plotService;

    @Autowired
    private IBasePropertyService<Premise> premiseService;

    @Autowired
    private IBaseClientPropertyService<House> clientHouseService;

    @Autowired
    private IBaseClientPropertyService<Premise> clientPremiseService;

    @Autowired
    private IBaseClientPropertyService<Plot> clientPlotService;

    @Override
    public BaseResponse listByHouse(Long idHouse) {
        List<GetClientResponse> list = clientHouseService.list(idHouse)
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
        List<GetClientResponse> list = clientPlotService.list(idPlot)
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
        List<GetClientResponse> list = clientPremiseService.list(idPremise)
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
    public BaseResponse createHouseClient(BaseClientRequest request, Long houseId) {
        Client client = from(request);
        client = repository.save(client);
        House house = houseService.getPropertyE(houseId);
        clientHouseService.create(client, house);
        return BaseResponse.builder()
                .data(from(client))
                .message("The prospective buyer was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse createPlotClient(BaseClientRequest request, Long idPlot) {
        Client client = from(request);
        client = repository.save(client);
        Plot plot = plotService.getPropertyE(idPlot);
        clientPlotService.create(client, plot);
        return BaseResponse.builder()
                .data(from(client))
                .message("The prospective buyer was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse createPremiseClient(BaseClientRequest request, Long idPremise) {
        Client client = from(request);
        client = repository.save(client);
        Premise premise = premiseService.getPropertyE(idPremise);
        clientPremiseService.create(client, premise);
        return BaseResponse.builder()
                .data(from(client))
                .message("The prospective buyer was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, BaseClientRequest request) {
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
        clientHouseService.delete(client);
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
        clientPlotService.delete(client);
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
        clientPremiseService.delete(client);
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

    private Client update(Client client, BaseClientRequest request){
        client.setName(request.getName());
        client.setContact(request.getContact());
        client.setBudget(request.getBudget());
        return repository.save(client);
    }

    private Client from(BaseClientRequest request){
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
