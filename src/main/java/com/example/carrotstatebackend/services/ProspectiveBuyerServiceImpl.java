package com.example.carrotstatebackend.services;

import com.example.carrotstatebackend.controllers.dtos.request.CreateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetProspectiveBuyerResponse;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.entities.Plot;
import com.example.carrotstatebackend.entities.Premise;
import com.example.carrotstatebackend.entities.ProspectiveBuyer;
import com.example.carrotstatebackend.repositories.IProspectiveBuyerRepository;
import com.example.carrotstatebackend.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class  ProspectiveBuyerServiceImpl implements IProspectiveBuyerService {

    @Autowired
    private IProspectiveBuyerRepository repository;
    
    @Autowired 
    private IHouseService houseService;

    @Autowired
    private IPlotService plotService;

    @Autowired
    private IPremiseService premiseService;

    @Autowired
    private IProspectiveBuyerHouseService prospectiveBuyerHouseService;

    @Autowired
    private IProspectiveBuyerPremiseService prospectiveBuyerPremiseService;

    @Autowired
    private IProspectiveBuyerPlotService prospectiveBuyerPlotService;

    @Override
    public BaseResponse listByHouse(Long idHouse) {
        List<GetProspectiveBuyerResponse> list = prospectiveBuyerHouseService.list(idHouse)
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
        List<GetProspectiveBuyerResponse> list = prospectiveBuyerPlotService.list(idPlot)
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
        List<GetProspectiveBuyerResponse> list = prospectiveBuyerPremiseService.list(idPremise)
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
    public BaseResponse createHouseProspectiveBuyer(CreateProspectiveBuyerRequest request, Long houseId) {
        ProspectiveBuyer prospectiveBuyer = from(request);
        prospectiveBuyer = repository.save(prospectiveBuyer);
        House house = houseService.getHouse(houseId);
        prospectiveBuyerHouseService.create(prospectiveBuyer, house);
        return BaseResponse.builder()
                .data(from(prospectiveBuyer))
                .message("The prospective buyer was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse createPlotProspectiveBuyer(CreateProspectiveBuyerRequest request, Long idPlot) {
        ProspectiveBuyer prospectiveBuyer = from(request);
        prospectiveBuyer = repository.save(prospectiveBuyer);
        Plot plot = plotService.getPlot(idPlot);
        prospectiveBuyerPlotService.create(prospectiveBuyer, plot);
        return BaseResponse.builder()
                .data(from(prospectiveBuyer))
                .message("The prospective buyer was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse createPremiseProspectiveBuyer(CreateProspectiveBuyerRequest request, Long idPremise) {
        ProspectiveBuyer prospectiveBuyer = from(request);
        prospectiveBuyer = repository.save(prospectiveBuyer);
        Premise premise = premiseService.getPremise(idPremise);
        prospectiveBuyerPremiseService.create(prospectiveBuyer, premise);
        return BaseResponse.builder()
                .data(from(prospectiveBuyer))
                .message("The prospective buyer was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateProspectiveBuyerRequest request) {
        ProspectiveBuyer prospectiveBuyer = findOneAndEnsureExist(id);
        prospectiveBuyer = update(prospectiveBuyer, request);
        return BaseResponse.builder()
                .data(from(prospectiveBuyer))
                .message("the agent was updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse deleteHouseProspective(Long id) {
        ProspectiveBuyer prospectiveBuyer = repository.findById(id)
                .orElseThrow(NotFoundException::new);
        prospectiveBuyerHouseService.delete(prospectiveBuyer);
        repository.delete(prospectiveBuyer);
        return BaseResponse.builder()
                .message("the prospective buyer was deleted")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse deletePlotProspective(Long id) {
        ProspectiveBuyer prospectiveBuyer = repository.findById(id)
                .orElseThrow(NotFoundException::new);
        prospectiveBuyerPlotService.delete(prospectiveBuyer);
        repository.delete(prospectiveBuyer);
        return BaseResponse.builder()
                .message("the prospective buyer was deleted")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public BaseResponse deletePremiseProspective(Long id) {
        ProspectiveBuyer prospectiveBuyer = repository.findById(id)
                .orElseThrow(NotFoundException::new);
        prospectiveBuyerPremiseService.delete(prospectiveBuyer);
        repository.delete(prospectiveBuyer);
        return BaseResponse.builder()
                .message("the prospective buyer was deleted")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ProspectiveBuyer getProspectiveBuyer(Long id){
        return findOneAndEnsureExist(id);
    }

    private ProspectiveBuyer findOneAndEnsureExist(Long id){
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    private ProspectiveBuyer update(ProspectiveBuyer prospectiveBuyer, UpdateProspectiveBuyerRequest request){
        prospectiveBuyer.setName(request.getName());
        prospectiveBuyer.setContact(request.getContact());
        prospectiveBuyer.setBudget(request.getBudget());
        return repository.save(prospectiveBuyer);
    }

    private ProspectiveBuyer from(CreateProspectiveBuyerRequest request){
        ProspectiveBuyer prospectiveBuyer = new ProspectiveBuyer();
        prospectiveBuyer.setName(request.getName());
        prospectiveBuyer.setContact(request.getContact());
        prospectiveBuyer.setBudget(request.getBudget());
        return prospectiveBuyer;
    }

    private GetProspectiveBuyerResponse from(ProspectiveBuyer prospectiveBuyer){
        GetProspectiveBuyerResponse response = new GetProspectiveBuyerResponse();
        response.setId(prospectiveBuyer.getId());
        response.setName(prospectiveBuyer.getName());
        response.setContact(prospectiveBuyer.getContact());
        response.setBudget(prospectiveBuyer.getBudget());
        return response;
    }

    private GetProspectiveBuyerResponse from(Long idProspectiveBuyer){
        return repository.findById(idProspectiveBuyer)
                .map(this::from)
                .orElseThrow(NotFoundException::new);
    }
}
