package com.example.carrotstatebackend.controllers;


import com.example.carrotstatebackend.controllers.dtos.request.properties.BaseHouseRequest;
import com.example.carrotstatebackend.controllers.dtos.request.RequestFilters;
import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.entities.House;
import com.example.carrotstatebackend.services.interfaces.properties.IBasePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("house")
public class HouseController {

    @Autowired
    private IBasePropertyService<House> service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id ){
        BaseResponse response = service.get(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/search/{keyWord}")
    public ResponseEntity<BaseResponse> search(@PathVariable String keyWord,
                                               @RequestBody @Valid RequestFilters filters){
        BaseResponse response = service.search(keyWord, filters);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse> list(){
        BaseResponse response = service.list();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/list/agent/{idAgent}")
    public ResponseEntity<BaseResponse> listByAgent(@PathVariable Long idAgent){
        BaseResponse response = service.listByAgent(idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/agent/{idAgent}")
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid BaseHouseRequest request,
                                               @PathVariable Long idAgent){
        BaseResponse response = service.create(request, idAgent);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("{idHouse}")
    public ResponseEntity<BaseResponse> update(@RequestBody @Valid BaseHouseRequest request,
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
