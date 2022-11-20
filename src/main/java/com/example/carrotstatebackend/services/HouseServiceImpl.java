package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.RequestFilters;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetHouseResponse;
import com.example.carrotstatebackend.controllers.exceptions.InvalidDeleteException;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.controllers.exceptions.NotValidCityCodeException;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.enums.CityState;
import com.example.carrotstatebackend.repositories.IHouseRepository;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import com.example.carrotstatebackend.services.interfaces.IHouseService;
import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public BaseResponse search(String keyWord, RequestFilters filters) {
        if (filters.getUseKeyWord()){
            List<GetHouseResponse> finalList = filter(filters).stream()
                    .filter(getHouseResponse -> evaluate(getHouseResponse, keyWord))
                    .collect(Collectors.toList());
            BaseResponse.builder()
                    .data(finalList)
                    .message("filter")
                    .success(true)
                    .httpStatus(HttpStatus.FOUND)
                    .build();
        }
        return BaseResponse.builder()
                .data(filter(filters))
                .message("filter")
                .success(true)
                .httpStatus(HttpStatus.FOUND)
                .build();
    }

    @Override
    public BaseResponse list() {
        List<GetHouseResponse> list = repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(list)
                .message("houses")
                .success(true)
                .httpStatus(HttpStatus.FOUND)
                .build();
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
    public BaseResponse update(Long idHouse, UpdateHouseRequest request) {
        House house = repository.findById(idHouse).orElseThrow(NotFoundException::new);
        return BaseResponse.builder()
                .data(from(update(house, request)))
                .message("the house was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
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
    public GetHouseResponse updateToSoldOut(Long idHouse, Client client) {
        House house = findOneAndEnsureExist(idHouse);
        house.setClient(client);
        house.setSoldOut(true);
        return from(house);
    }

    public House getHouse(Long id){
        return findOneAndEnsureExist(id);
    }

    @Override
    public BaseResponse delete(Long id) {
        House house = repository.findById(id)
                .orElseThrow(NotFoundException::new);
        if (house.getClient() != null) throw new InvalidDeleteException();
        repository.deleteById(id);
        return BaseResponse.builder()
                .message("the house was deleted")
                .httpStatus(HttpStatus.ACCEPTED)
                .success(true).build();
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
        response.setCityState(house.getCityState());
        return response;
    }

    private House update(House house, UpdateHouseRequest request){
        house.setBathRoomNum(request.getBathRoomNum());
        house.setDescription(request.getDescription());
        house.setFloors(request.getFloors());
        house.setLocation(request.getLocation());
        house.setRooms(request.getRooms());
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
        house.setCityState(from(request.getCityCode()));
        house.setRooms(request.getRooms());
        house.setSoldOut(false);
        return house;
    }

    private CityState from(String cityCode){
        return Stream.of(CityState.values())
                .filter(c -> c.getLocationCode().equals(cityCode))
                .findFirst().orElseThrow(() -> new NotValidCityCodeException(cityCode));
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

    private List<GetHouseResponse> filter(RequestFilters filters) {
        if (filters.getBudget() != null && filters.getCityCode() != null){
            return repository.findAllByPriceIsLessThanEqualAndCityState(
                    filters.getBudget(), from(filters.getCityCode()))
                    .stream()
                    .map(this::from).collect(Collectors.toList());

        }
        if (filters.getBudget() != null){
            return repository.findAllByPriceIsLessThanEqual(filters.getBudget())
                    .stream().map(this::from).collect(Collectors.toList());

        }
        return repository.findAllByCityState(from(filters.getCityCode()))
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private Boolean evaluate(GetHouseResponse house, String keyWord){
        return house
                .getName()
                .contains(keyWord) || house.getDescription().contains(keyWord);
    }

}

//base response,
