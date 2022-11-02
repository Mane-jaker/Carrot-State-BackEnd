package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdatePlotRequest;
import com.example.carrotstatebackend.controllers.dtos.response.GetPlotResponse;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.repositories.IPlotRepository;
import com.example.carrotstatebackend.services.interfaces.IPlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlotServiceImpl implements IPlotService {

    @Autowired
    private IPlotRepository repository;

    @Override
    public List<GetPlotResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public GetPlotResponse get(Long id) {
        return from(id);
    }

    @Override
    public void delete(Long id) {repository.deleteById(id);

    }

    public Plot getPlot(Long id){
        return findOneAndEnsureExist(id);
    }

    @Override
    public GetPlotResponse create(CreatePlotRequest request) {
        Plot plot = from(request);
        return from(repository.save(plot));
    }

    @Override
    public GetPlotResponse update(Long id, UpdatePlotRequest request) {

        Plot plot = findOneAndEnsureExist(id);
        plot = update(plot, request);
        return from(plot);

    }

    private Plot findOneAndEnsureExist(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The Plot does not exist"));
    }

    private GetPlotResponse from(Plot plot){
        GetPlotResponse response = new GetPlotResponse();
        response.setId(plot.getId());
        response.setDescription(plot.getDescription());
        response.setLocation(plot.getLocation());
        response.setPrice(plot.getPrice());
        response.setSize(plot.getSize());
        response.setName(plot.getName());
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
        return plot;
    }

    private GetPlotResponse from(Long idPlot){
        return repository.findById(idPlot).map(this::from).orElseThrow(()-> new RuntimeException("no ta tu plot papito"));
    }
}
