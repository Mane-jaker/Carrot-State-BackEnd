package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.properties.BasePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.request.properties.BasePropertyRequest;
import com.example.carrotstatebackend.controllers.dtos.request.RequestFilters;
import com.example.carrotstatebackend.controllers.dtos.response.*;
import com.example.carrotstatebackend.controllers.dtos.response.properties.GetPlotResponse;
import com.example.carrotstatebackend.exceptions.InvalidDeleteException;
import com.example.carrotstatebackend.exceptions.NotFoundException;
import com.example.carrotstatebackend.exceptions.NotValidCityCodeException;
import com.example.carrotstatebackend.entities.Agent;
import com.example.carrotstatebackend.entities.Client;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.enums.CityState;
import com.example.carrotstatebackend.entities.pivots.ImagePlot;
import com.example.carrotstatebackend.repositories.properties.IPlotRepository;
import com.example.carrotstatebackend.services.evaluators.FiltersUtilityImpl;
import com.example.carrotstatebackend.services.evaluators.IFilterUtility;
import com.example.carrotstatebackend.services.interfaces.persons.IAgentService;
import com.example.carrotstatebackend.services.interfaces.properties.IBasePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PlotServiceImpl implements IBasePropertyService<Plot> {

    @Autowired
    private IPlotRepository repository;

    @Autowired
    private IAgentService agentService;

    @Autowired
    private IFilterUtility filtersUtility;

    @Override
    public BaseResponse listByAgent(Long idAgent) {
        return BaseResponse.builder()
                .data(getList(idAgent))
                .message("List")
                .success(true)
                .httpStatus(HttpStatus.FOUND)
                .build();
    }

    @Override
    public BaseResponse get(Long id) {
        GetPlotResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("the plot was find")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse list() {
        List<GetPlotResponse> list = repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(list)
                .message("plots")
                .httpStatus(HttpStatus.FOUND)
                .success(true).build();
    }

    @Override
    public BaseResponse search(String keyWord, RequestFilters filters) {
        if (filters.getUseKeyWord()){
            List<GetPlotResponse> finalList = filter(filters).stream()
                    .filter(getPlotResponse -> evaluate(getPlotResponse, keyWord))
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
    public BaseResponse create(BasePropertyRequest request, Long idAgent) {
        Plot plot = from((BasePlotRequest) request);
        Agent agent = agentService.getAgent(idAgent);
        plot.setAgent(agent);
        GetPlotResponse response = from(repository.save(plot));
        agent.setNumberOfProperties(agent.getNumberOfProperties() + 1);
        agentService.update(agent);
        return BaseResponse.builder()
                .data(response)
                .message("the plot was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long idPlot, BasePropertyRequest request) {
        Plot plot = repository.findById(idPlot).orElseThrow(NotFoundException::new);
        return BaseResponse.builder()
                .data(from(update(plot, (BasePlotRequest) request)))
                .message("the plot was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse delete(Long id){
        Plot plot = repository.findById(id).orElseThrow(NotFoundException::new);
        if (plot.getClient() != null) throw new InvalidDeleteException();
        repository.delete(plot);
        return BaseResponse.builder()
                .message("the plot was deleted")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public Plot getPropertyE(Long id){ return findOneAndEnsureExist(id); }


    @Override
    public BaseResponse updateToSoldOut(Long idPlot, Client owner){
        Plot plot = findOneAndEnsureExist(idPlot);
        plot.setClient(owner);
        plot.setSoldOut(true);
        return BaseResponse.builder()
                .data(from(repository.save(plot)))
                .message("updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    private Plot findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private List<GetImageResponse> from(List<ImagePlot> image){
        return image.stream()
                .map(imagePlot -> GetImageResponse.builder().url(imagePlot.getUrl()).build())
                .collect(Collectors.toList());
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
        response.setCityState(plot.getCityState());
        response.setImages(from(plot.getImagesPlot()));
        return response;
    }

    private  Plot update(Plot plot, BasePlotRequest request){
        plot.setDescription(request.getDescription());
        plot.setLocation(request.getLocation());
        plot.setName(request.getName());
        plot.setPrice(request.getPrice());
        plot.setSize(request.getSize());
        return repository.save(plot);
    }

    private  Plot from(BasePlotRequest request){
        Plot plot = new Plot();
        plot.setDescription(request.getDescription());
        plot.setLocation(request.getLocation());
        plot.setSize(request.getSize());
        plot.setName(request.getName());
        plot.setPrice(request.getPrice());
        plot.setCityState(from(request.getCityCode()));
        plot.setSoldOut(false);
        return plot;
    }

    private CityState from(String cityCode){
        return Stream.of(CityState.values())
                .filter(c -> c.getLocationCode().equals(cityCode))
                .findFirst().orElseThrow(() -> new NotValidCityCodeException(cityCode));
    }

    private GetPlotResponse from(Long idPlot){
        return repository.findById(idPlot)
                .map(this::from)
                .orElseThrow(NotFoundException::new);
    }

    private List<GetPlotResponse> getList(Long idAgent){
        Agent agent = agentService.getAgent(idAgent);
        return repository
                .findAllByAgent(agent)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private List<GetPlotResponse> filter(RequestFilters filters) {

        switch (filtersUtility.filter(filters)){
            case BY_CITY_CODE:
                return repository.findAllByCityState(from(filters.getCityCode()))
                        .stream()
                        .map(this::from)
                        .collect(Collectors.toList());
            case BY_PRICE:
                return repository.findAllByPriceIsLessThanEqual(filters.getBudget())
                        .stream().map(this::from).collect(Collectors.toList());
            case BY_ALL_FILTERS:
                return repository.findAllByPriceIsLessThanEqualAndCityState(
                                filters.getBudget(), from(filters.getCityCode()))
                        .stream()
                        .map(this::from)
                        .collect(Collectors.toList());
            default:
                throw new RuntimeException();
        }
    }

    private Boolean evaluate(GetPlotResponse premise, String keyWord){
        return premise
                .getName()
                .contains(keyWord) || premise.getDescription().contains(keyWord);
    }

}
