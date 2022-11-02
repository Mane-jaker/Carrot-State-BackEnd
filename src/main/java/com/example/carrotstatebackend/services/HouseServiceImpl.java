package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetHouseResponse;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.repositories.IHouseRepository;
import com.example.carrotstatebackend.services.interfaces.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl implements IHouseService{

    @Autowired
    private IHouseRepository repository;

    @Override
    public List<GetHouseResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public GetHouseResponse get(Long id) {
        return from(id);
    }

    @Override
    public void delete(Long id) {repository.deleteById(id);

    }

    public House getHouse(Long id){
        return findOneAndEnsureExist(id);
    }

    @Override
    public GetHouseResponse create(CreateHouseRequest request) {
        House house = from(request);
        return from(repository.save(house));
    }

    @Override
    public GetHouseResponse update(Long id, UpdateHouseRequest request) {

        House house = findOneAndEnsureExist(id);
        house = update(house, request);
        return from(house);
        
    }

    private House findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }


    private GetHouseResponse from(House house){
        GetHouseResponse response = new GetHouseResponse();
        response.setId(house.getId());
        response.setBathroomNum(house.getBathroomNum());
        response.setDescription(house.getDescription());
        response.setLocation(house.getLocation());
        response.setFloors(house.getFloors());
        response.setRooms(house.getRooms());
        return response;
    }

    private  House update(House house, UpdateHouseRequest request){
        house.setBathroomNum(request.getBathroomNum());
        house.setDescription(request.getDescription());
        house.setFloors(request.getFloors());
        house.setLocation(request.getLocation());
        house.setRooms(request.getRooms());
        return repository.save(house);
    }

    private  House from(CreateHouseRequest request){
        House house = new House();
        house.setBathroomNum(request.getBathroomNum());
        house.setDescription(request.getDescription());
        house.setFloors(request.getFloors());
        house.setLocation(request.getLocation());
        house.setRooms(request.getRooms());
        return house;
    }

    
    private GetHouseResponse from(Long idHouse){
        return repository.findById(idHouse).map(this::from).orElseThrow(()-> new RuntimeException("no ta tu casita papito"));
    }
}
