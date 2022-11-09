package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetHouseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetOwnerResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Owner;
import com.example.carrotstatebackend.repositories.IHouseRepository;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import com.example.carrotstatebackend.services.interfaces.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl implements IHouseService{

    @Autowired
    private IHouseRepository repository;

    @Autowired
    private IAgentService agentService;


    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(from(id))
                .message("found")
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public void delete(Long id) {repository.deleteById(id);}

    public House getHouse(Long id){
        return findOneAndEnsureExist(id);
    }

    @Override
    public BaseResponse create(CreateHouseRequest request, Long idAgent) {
        House house = from(request);
        Agent agent = agentService.getAgent(idAgent);
        house.setAgent(agent);
        GetHouseResponse response = from(repository.save(house));
        agent.setNumberOfProperties(agent.getNumberOfProperties() + 1);
        agentService.update(agent);
        return BaseResponse.builder()
                .data(response)
                .message("the house was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse listByAgent(Long idAgent) {
        return BaseResponse.builder()
                .data(getList(idAgent))
                .message("found")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse update(Long idHouse, UpdateHouseRequest request) {
        House house = repository.findById(idHouse).orElseThrow(NotFoundException::new);
        return BaseResponse.builder()
                .data(from(update(house, request)))
                .message("the house was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public GetHouseResponse updateToSoldOut(Long idHouse, Owner owner) {
        House house = findOneAndEnsureExist(idHouse);
        house.setOwner(owner);
        house.setSoldOut(true);
        return from(house);
    }

    private House findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
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

    private GetOwnerResponse from(Owner owner){
        GetOwnerResponse response = new GetOwnerResponse();
        response.setId(owner.getId());
        response.setName(owner.getName());
        response.setContact(owner.getContact());
        return response;
    }

    private House update(House house, UpdateHouseRequest request){
        house.setBathRoomNum(request.getBathroomNum());
        house.setDescription(request.getDescription());
        house.setFloors(request.getFloors());
        house.setLocation(request.getLocation());
        house.setRooms(request.getRooms());
        house.setSoldOut(request.getSoldOut());
        house.setSize(request.getSize());
        house.setName(request.getName());
        house.setPrice(request.getPrice());
        return repository.save(house);
    }

    private  House from(CreateHouseRequest request){
        House house = new House();
        house.setName(request.getName());
        house.setBathRoomNum(request.getBathRoomNum());
        house.setDescription(request.getDescription());
        house.setSize(request.getSize());
        house.setPrice(request.getPrice());
        house.setFloors(request.getFloors());
        house.setLocation(request.getLocation());
        house.setRooms(request.getRooms());
        house.setSoldOut(false);
        return house;
    }

    
    private GetHouseResponse from(Long idHouse){
        return repository.findById(idHouse)
                .map(this::from)
                .orElseThrow(NotFoundException::new);
    }

    private List<GetHouseResponse> getList(Long idAgent){
        Agent agent = agentService.getAgent(idAgent);
        return repository
                .findAllByAgent(agent)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }




}

//base response,
