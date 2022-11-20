package com.example.carrotstatebackend.controllers;

import com.example.carrotstatebackend.controllers.dtos.request.CreateClientRequest;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateClientRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("client")
public class ClientController {

    @Autowired
    private IClientService service;

    @GetMapping("/house/{idHouse}")
    public ResponseEntity<BaseResponse> listHouseClient(@PathVariable Long idHouse){
        BaseResponse response = service.listByHouse(idHouse);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/premise/{idPremise}")
    public ResponseEntity<BaseResponse> listPremiseClient(@PathVariable Long idPremise){
        BaseResponse response = service.listByPremise(idPremise);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/plot/{idPlot}")
    public ResponseEntity<BaseResponse> listPlotClient(@PathVariable Long idPlot){
        BaseResponse response = service.listByPlot(idPlot);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("house/{idHouse}")
    public ResponseEntity<BaseResponse> createHouseClient(
            @RequestBody @Valid CreateClientRequest request, @PathVariable Long idHouse){
        BaseResponse baseResponse = service.createHouseClient(request, idHouse);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("plot/{idPlot}")
    public ResponseEntity<BaseResponse> createPlotClient(
            @RequestBody @Valid CreateClientRequest request, @PathVariable Long idPlot){
        BaseResponse baseResponse = service.createPlotClient(request, idPlot);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("premise/{idPremise}")
    public ResponseEntity<BaseResponse> createPremiseClient(
            @RequestBody @Valid CreateClientRequest request, @PathVariable Long idPremise){
        BaseResponse baseResponse = service.createPremiseClient(request, idPremise);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> updateClient(@RequestBody @Valid UpdateClientRequest request,
                                               @PathVariable Long id){
        BaseResponse response = service.update(id, request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("/house/{idClient}")
    public ResponseEntity<BaseResponse> deleteHouseClient(@PathVariable Long idClient){
        BaseResponse response = service.deleteHouseClient(idClient);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("/plot/{idClient}")
    public ResponseEntity<BaseResponse> deletePlotClient(@PathVariable Long idClient){
        BaseResponse response = service.deletePlotClient(idClient);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("/premise/{idClient}")
    public ResponseEntity<BaseResponse> deletePremiseClient(@PathVariable Long idClient){
        BaseResponse response = service.deletePremiseClient(idClient);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}
