package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.persons.BaseClientRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.persons.GetClientResponse;
import com.example.carrotstatebackend.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.repositories.persons.IClientRepository;
import com.example.carrotstatebackend.services.interfaces.persons.IClientService;
import com.example.carrotstatebackend.services.interfaces.pivtos.IBaseClientPropertyService;
import com.example.carrotstatebackend.services.interfaces.properties.IBasePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    private final String DEFAULT_PICTURE = "https://conejobucket.s3.us-east-2.amazonaws.com/persons/default/profile/images.jpeg";


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
    public BaseResponse createClient(BaseClientRequest request) {
        GetClientResponse response = from(repository.save(from(request)));
        return BaseResponse.builder()
                .data(response)
                .message("client saved")
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse createHouseClient(BaseClientRequest request, Long houseId) {
        return null;
    }

    @Override
    public BaseResponse createPlotClient(BaseClientRequest request, Long idPlot) {
        return null;
    }

    @Override
    public BaseResponse createPremiseClient(BaseClientRequest request, Long idPremise) {
        return null;
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
       return null;
    }

    @Override
    public BaseResponse deletePlotClient(Long id) {
        return null;
    }

    @Override
    public BaseResponse deletePremiseClient(Long id) {
        return null;
    }

    @Override
    public Client getClient(Long id){
        return findOneAndEnsureExist(id);
    }

    @Override
    public Optional<Client> getClient(String email) {
        return repository.findByEmail(email);
    }

    private Client findOneAndEnsureExist(Long id){
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private Client update(Client client, BaseClientRequest request){
        client.setPassword(request.getPassword());
        client.setEmail(request.getEmail());
        client.setName(request.getName());
        client.setContact(request.getContact());
        client.setBudget(request.getBudget());
        return repository.save(client);
    }

    private Client from(BaseClientRequest request){
        Client client = new Client();
        client.setEmail(request.getEmail());
        client.setPassword(request.getPassword());
        client.setName(request.getName());
        client.setContact(request.getContact());
        client.setBudget(request.getBudget());
        client.setProfilePicture(DEFAULT_PICTURE);
        return client;
    }

    private GetClientResponse from(Client client){
        return GetClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .contact(client.getContact())
                .budget(client.getBudget())
                .email(client.getEmail())
                .profilePicture(client.getProfilePicture()).build();
    }

    private GetClientResponse from(Long idClient){
        return repository.findById(idClient)
                .map(this::from)
                .orElseThrow(NotFoundException::new);
    }
}
