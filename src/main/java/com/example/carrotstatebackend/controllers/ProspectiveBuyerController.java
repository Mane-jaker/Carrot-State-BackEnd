package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateProspectiveBuyerRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetProspectiveBuyerResponse;
import com.example.carrotstatebackend.services.interfaces.IProspectiveBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("prospective_buyer")
public class ProspectiveBuyerController {

    @Autowired
    private IProspectiveBuyerService service;

    @GetMapping("/house/{idHouse}")
    public ResponseEntity<BaseResponse> listPHuse(@PathVariable Long idHouse){
        BaseResponse response = service.listByHouse(idHouse);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/premise/{idPremise}")
    public ResponseEntity<BaseResponse> listPPremise(@PathVariable Long idPremise){
        BaseResponse response = service.listByPremise(idPremise);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/plot/{idPlot}")
    public ResponseEntity<BaseResponse> listPPlot(@PathVariable Long idPlot){
        BaseResponse response = service.listByPlot(idPlot);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("house/{idHouse}")
    public ResponseEntity<BaseResponse> createHProspectiveBuyer(
            @RequestBody @Valid CreateProspectiveBuyerRequest request, @PathVariable Long idHouse){
        BaseResponse baseResponse = service.createHouseProspectiveBuyer(request, idHouse);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("plot/{idPlot}")
    public ResponseEntity<BaseResponse> createPlotProspectiveBuyer(
            @RequestBody @Valid CreateProspectiveBuyerRequest request, @PathVariable Long idPlot){
        BaseResponse baseResponse = service.createPlotProspectiveBuyer(request, idPlot);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("premise/{idPremise}")
    public ResponseEntity<BaseResponse> createPremiseProspectiveBuyer(
            @RequestBody @Valid CreateProspectiveBuyerRequest request, @PathVariable Long idPremise){
        BaseResponse baseResponse = service.createPremiseProspectiveBuyer(request, idPremise);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public GetProspectiveBuyerResponse update(@PathVariable Long id,
                                              @RequestBody @Valid UpdateProspectiveBuyerRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Long id) { service.delete(id);}
}
