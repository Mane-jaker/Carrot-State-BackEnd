package com.example.carrotstatebackend.controllers;


import com.example.carrotstatebackend.controllers.dtos.request.CreateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.RequestFilters;
import com.example.carrotstatebackend.controllers.dtos.request.UpdateHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.services.interfaces.IHouseService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("house")
public class HouseController {

    @Autowired
    private IHouseService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id ){
        BaseResponse response = service.get(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/search/{keyWord}")
    public ResponseEntity<BaseResponse> search(@PathVariable String keyWord,
                                               @RequestBody @Valid RequestFilters filters){
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse> list(){
        return null;
    }

    @GetMapping("/listByPrice/{price}")
    public ResponseEntity<BaseResponse> listByPrice(@PathVariable Float price){
        return null;
    }

    @GetMapping("/listByLocation/{locationCode}")
    public ResponseEntity<BaseResponse> listByLocation(@PathVariable String locationCode){
        return null;
    }

    @GetMapping("/list/agent/{idAgent}")
    public ResponseEntity<BaseResponse> listByAgent(@PathVariable Long idAgent){
        BaseResponse response = service.listByAgent(idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/agent/{idAgent}")
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateHouseRequest request,
                                               @PathVariable Long idAgent){
        BaseResponse response = service.create(request, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("{idHouse}")
    public ResponseEntity<BaseResponse> update(@RequestBody @Valid UpdateHouseRequest request,
                                                @PathVariable Long idHouse){
        BaseResponse response = service.update(idHouse, request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("/{idHouse}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long idHouse){
        BaseResponse response = service.delete(idHouse);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}
