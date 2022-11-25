package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.RequestFilters;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePremiseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetImageResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetPremiseResponse;
import com.example.carrotstatebackend.controllers.exceptions.InvalidDeleteException;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.controllers.exceptions.NotValidCityCodeException;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.enums.CityState;
import com.example.carrotstatebackend.entities.pivots.ImagePlot;
import com.example.carrotstatebackend.entities.pivots.ImagePremise;
import com.example.carrotstatebackend.repositories.IPremiseRepository;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import com.example.carrotstatebackend.services.interfaces.IPremiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PremiseServiceImpl implements IPremiseService {

    @Autowired
    private IPremiseRepository repository;

    @Autowired
    private IAgentService agentService;


    @Override
    public  BaseResponse listByAgent(Long idAgent){
        return BaseResponse.builder()
                .data(getList(idAgent))
                .message("List")
                .success(true)
                .httpStatus(HttpStatus.FOUND)
                .build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetPremiseResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("the premise was find")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse list() {
        List<GetPremiseResponse> list = repository.findAll()
                .stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(list)
                .success(true)
                .message("list")
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse search(String keyWord, RequestFilters filters) {
        if (filters.getUseKeyWord()){
            List<GetPremiseResponse> finalList = filter(filters).stream()
                    .filter(getPremiseResponse -> evaluate(getPremiseResponse, keyWord))
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
    public BaseResponse create(CreatePremiseRequest request, Long idAgent) {
        Premise premise = from(request);
        Agent agent = agentService.getAgent(idAgent);
        premise.setAgent(agent);
        GetPremiseResponse response = from(repository.save(premise));
        agent.setNumberOfProperties(agent.getNumberOfSales() + 1);
        agentService.update(agent);
        return BaseResponse.builder()
                .data(response)
                .message("the premise was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, UpdatePremiseRequest request) {
        Premise premise = findOneAndEnsureExist(id);
        premise = update(premise, request);
        return BaseResponse.builder()
                .data(from(premise))
                .message("the premise was uploaded")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse delete(Long id) {
        Premise premise = repository.findById(id).orElseThrow(NotFoundException::new);
        if (premise.getClient() != null) throw new InvalidDeleteException();
        repository.delete(premise);
        return BaseResponse.builder()
                .message("the premise was deleted")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    public Premise getPremise(Long id){
        return findOneAndEnsureExist(id);
    }

    @Override
    public GetPremiseResponse updateToSoldOut(Long idPlot, Client owner){
        Premise premise = findOneAndEnsureExist(idPlot);
        premise.setClient(owner);
        premise.setSoldOut(true);
        return from(repository.save(premise));
    }

    private Premise findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private GetPremiseResponse from(Premise premise){
       GetPremiseResponse response = new GetPremiseResponse();
       response.setId(premise.getId());
       response.setDescription(premise.getDescription());
       response.setLocation(premise.getLocation());
       response.setName(premise.getName());
       response.setPrice(premise.getPrice());
       response.setSize(premise.getSize());
       response.setCityState(premise.getCityState());
       response.setImages(from(premise.getImagePremises()));
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
        premise.setSoldOut(false);
        premise.setCityState(from(request.getCityCode()));
        return premise;
    }

    private List<GetImageResponse> from(List<ImagePremise> image){
        return image.stream()
                .map(imagePremise -> GetImageResponse.builder().url(imagePremise.getUrl()).build())
                .collect(Collectors.toList());
    }

    private CityState from(String cityCode){
        return Stream.of(CityState.values())
                .filter(c -> c.getLocationCode().equals(cityCode))
                .findFirst().orElseThrow(() -> new NotValidCityCodeException(cityCode));
    }

    private GetPremiseResponse from(Long idPremise){
        return repository.findById(idPremise).map(this::from).orElseThrow(NotFoundException::new);
    }

    private List<GetPremiseResponse> getList(Long idAgent){
        Agent agent = agentService.getAgent(idAgent);
        return repository
                .findAllByAgent(agent)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private List<GetPremiseResponse> filter(RequestFilters filters) {
        if (filters.getBudget() != null && filters.getCityCode() != null){
            return repository.findAllByPriceIsLessThanEqualAndCityState(
                            filters.getBudget(), from(filters.getCityCode()))
                    .stream()
                    .map(this::from)
                    .collect(Collectors.toList());
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

    private Boolean evaluate(GetPremiseResponse premise, String keyWord){
        return premise
                .getName()
                .contains(keyWord) || premise.getDescription().contains(keyWord);
    }


}
