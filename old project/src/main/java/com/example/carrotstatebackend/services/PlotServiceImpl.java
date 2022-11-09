package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetPlotResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.*;
import com.example.carrotstatebackend.repositories.IPlotRepository;
import com.example.carrotstatebackend.services.interfaces.IAgentService;
import com.example.carrotstatebackend.services.interfaces.IPlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlotServiceImpl implements IPlotService {

    @Autowired
    private IPlotRepository repository;

    @Autowired
    private IAgentService agentService;

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
    public void delete(Long id) {repository.deleteById(id);}

    public Plot getPlot(Long id){
        return findOneAndEnsureExist(id);
    }

    @Override
    public BaseResponse create(CreatePlotRequest request, Long idAgent) {
        Plot plot = from(request);
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
    public BaseResponse update(Long idPlot, UpdatePlotRequest request) {
        Plot plot = repository.findById(idPlot).orElseThrow(NotFoundException::new);
        return BaseResponse.builder()
                .data(from(update(plot, request)))
                .message("the plot was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public GetPlotResponse updateToSoldOut(Long idPlot, Owner owner){
        Plot plot = findOneAndEnsureExist(idPlot);
        plot.setOwner(owner);
        plot.setSoldOut(true);
        return from(repository.save(plot));
    }

    private Plot findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
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
        return response;
    }

    private  Plot update(Plot plot, UpdatePlotRequest request){
        plot.setDescription(request.getDescription());
        plot.setLocation(request.getLocation());
        plot.setName(request.getName());
        plot.setPrice(request.getPrice());
        plot.setSize(request.getSize());
        return repository.save(plot);
    }

    private  Plot from(CreatePlotRequest request){
        Plot plot = new Plot();
        plot.setDescription(request.getDescription());
        plot.setLocation(request.getLocation());
        plot.setSize(request.getSize());
        plot.setName(request.getName());
        plot.setPrice(request.getPrice());
        plot.setSoldOut(false);
        return plot;
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
}
