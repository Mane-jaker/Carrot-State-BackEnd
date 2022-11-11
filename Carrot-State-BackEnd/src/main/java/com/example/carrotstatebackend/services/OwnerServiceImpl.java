package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateOwnerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.CreateSoldRequest;
import com.example.carrotstatebackend.controllers.dtos.response.*;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.*;
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
        soldService.create(soldRequest);
        return BaseResponse.builder()
                .data(from(owner, premiseResponse))
                .message("the owner was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse listOwnerHouses(Long idOwner) {
        return BaseResponse.builder()
                .data(getHouseList(idOwner))
                .success(true)
                .message("houses from owner: " + idOwner)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse listOwnerPlots(Long idOwner) {
        return BaseResponse.builder()
                .data(getPlotList(idOwner))
                .success(true)
                .httpStatus(HttpStatus.FOUND)
                .message("Plot from owner: " + idOwner).build();
    }

    @Override
    public BaseResponse listOwnerPremises(Long idOwner) {
        return BaseResponse.builder()
                .data(getPremisesList(idOwner))
                .success(true)
                .httpStatus(HttpStatus.FOUND)
                .message("Premises from owner: " + idOwner).build();
    }

    @Override
    public Owner getOwner(Long idOwner) {
        return findOneAndEnsureExist(idOwner);
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

    private CreateOwnerResponse from(Owner owner, Object property){
        CreateOwnerResponse response = new CreateOwnerResponse();
        response.setId(owner.getId());
        response.setName(owner.getName());
        response.setContact(owner.getContact());
        response.setProperty(property);
        return response;
    }

    private GetOwnerResponse from(Owner owner){
        GetOwnerResponse response = new GetOwnerResponse();
        response.setId(owner.getId());
        response.setName(owner.getName());
        response.setContact(owner.getContact());
        return response;
    }

    private GetHouseResponse from(House house){
        GetHouseResponse response = new GetHouseResponse();
        response.setId(house.getId());
        response.setBathroomNum(house.getBathRoomNum());
        response.setDescription(house.getDescription());
        response.setLocation(house.getLocation());
        response.setFloors(house.getFloors());
        response.setRooms(house.getRooms());
        response.setSoldOut(house.getSoldOut());
        response.setPrice(house.getPrice());
        if (house.getOwner() != null) response.setOwner(from(house.getOwner()));
        return response;
    }

    private GetPlotResponse from(Plot plot){
        GetPlotResponse response = new GetPlotResponse();
        response.setId(plot.getId());
        response.setDescription(plot.getDescription());
        response.setLocation(plot.getLocation());
        response.setPrice(plot.getPrice());
        response.setSize(plot.getSize());
        response.setName(plot.getName());
        response.setSoldOut(plot.getSoldOut());
        if (plot.getOwner() != null) response.setOwner(from(plot.getOwner()));
        return response;
    }

    private GetPremiseResponse from(Premise premise){
        GetPremiseResponse response = new GetPremiseResponse();
        response.setId(premise.getId());
        response.setDescription(premise.getDescription());
        response.setLocation(premise.getLocation());
        response.setName(premise.getName());
        response.setPrice(premise.getPrice());
        response.setSize(premise.getSize());
        if (premise.getOwner() != null) response.setOwner(from(premise.getOwner()));
        return response;
    }

    private List<GetOwnerResponse> getList(){
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private List<GetHouseResponse> getHouseList(Long idOwner){
        return findOneAndEnsureExist(idOwner)
                .getHouses()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private List<GetPlotResponse> getPlotList(Long idOwner){
        return findOneAndEnsureExist(idOwner)
                .getPlots()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private List<GetPremiseResponse> getPremisesList(Long idOwner){
        return findOneAndEnsureExist(idOwner)
                .getPremises()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }
}
